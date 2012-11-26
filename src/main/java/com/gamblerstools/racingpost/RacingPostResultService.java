package com.gamblerstools.racingpost;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gamblerstools.model.Course;
import com.gamblerstools.model.FinishingPosition;
import com.gamblerstools.model.Horse;
import com.gamblerstools.model.Meeting;
import com.gamblerstools.model.Race;
import com.gamblerstools.model.RaceEntry;
import com.gamblerstools.model.RaceOutcome;
import com.gamblerstools.model.Result;
import com.gamblerstools.racingpost.model.RacingPostCourse;
import com.gamblerstools.racingpost.model.RacingPostFinishingPosition;
import com.gamblerstools.racingpost.model.RacingPostHorse;
import com.gamblerstools.racingpost.model.RacingPostMeeting;
import com.gamblerstools.racingpost.model.RacingPostRace;
import com.gamblerstools.results.service.ResultService;
import com.gamblerstools.util.DocumentRetriever;

public class RacingPostResultService implements ResultService {
	final static Logger logger = LoggerFactory.getLogger(RacingPostResultService.class);
	
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DocumentRetriever documentRetriever;
	
	public RacingPostResultService (DocumentRetriever documentRetriever) {
		this.documentRetriever = documentRetriever;
	}
	@Override
	public List<Result> getResults(Calendar date) {
		List<Result> results = new ArrayList<>();
		Set<Integer> raceIds = getRaceIdsOnDate(date);
		for(Integer raceId:raceIds) {
			results.add(getRaceResult(raceId));
		}
		return results;
	}
	private Result getRaceResult(int raceId) {
		Document doc = documentRetriever.getDocument("http://www.racingpost.com/horses/result_home.sd?race_id="+raceId);

		return parseRaceResult(doc);
		
	}

	private Result parseRaceResult(Document doc) {
		// TODO Auto-generated method stub
		logger.debug(getCourse(doc).toString());
		logger.debug(getMeeting(doc).toString());
		logger.debug(getRace(doc).toString());
		
		return null;
	}
	private Race getRace(Document doc) {
		
		Meeting meeting = getMeeting(doc);
		
		String timeString = extractString("(\\d{1,2}:\\d{2})", ".timeNavigation", doc);
		timeString+=" "+meeting.getMeetingDate().getYear();
		timeString+=" "+meeting.getMeetingDate().getMonthOfYear();
		timeString+=" "+meeting.getMeetingDate().getDayOfMonth();
		DateTime raceTime = DateTimeFormat.forPattern("kk:mm yyyy MM dd").parseDateTime(timeString);
		
		String name = extractString("</span>(.*)", ".leftColBig h3", doc);
		//"raceId":"561678"
		String raceId = extractString("raceId\":\"(\\d+)\"", "html", doc);
		List<RaceEntry> raceEntries = getRaceEntries(doc);
		//TODO Use a factory to create this
		return new RacingPostRace(raceId, meeting, raceTime, name);
	}
	private List<RaceEntry> getRaceEntries(Document doc) {
		List<RaceEntry> raceEntries = new ArrayList<>();
		
		Elements entries = doc.select(".resultRaceGrid tbody");
		
		for(Element entry:entries) {
			Element entryNode = entry.select("tr:eq(1)").first();
			FinishingPosition position = parsePosition(entryNode.select("td:eq(1) h3").first().text().trim());
			Horse horse = parseHorse(entryNode.select("td:eq(3) b a").first());
			logger.debug(horse.toString());
			
			//throw new RuntimeException("CONTINUE FROM HERE complete the race entry, neeed to parse specifics about the entry, i.e. weight etc.");
		}
		return raceEntries;
	}
	private Horse parseHorse(Element horseNode) {
		//http://rules.britishhorseracing.com/Orders-and-rules&staticID=126780&depth=3
		//name (optional countrycode) price
		//"(.*) .*?$"
		
		String expression = "horse_id=(\\d+)";
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(horseNode.toString());
		matcher.find();
		String id = matcher.group(1).trim();
		String name = horseNode.text();
		
		Horse horse = new RacingPostHorse(id, name);
		return horse;
	}
	private FinishingPosition parsePosition(String positionString) {
		RaceOutcome outcome = RaceOutcome.FINISHED;
		Integer position = null;
		try {
			position = Integer.parseInt(positionString);
		} catch (NumberFormatException nfe) {
			if(positionString.equalsIgnoreCase("PU")) {
				outcome = RaceOutcome.PULLED_UP;
			} else if(positionString.equalsIgnoreCase("F")) {
				outcome = RaceOutcome.FELL;
			} else {
				logger.debug("Cannot parse position: "+positionString);
				outcome = RaceOutcome.UNKNOWN;
			}
		}
		return new RacingPostFinishingPosition(outcome, position);
	}
	private Meeting getMeeting(Document doc) {
		Course course = getCourse(doc);
		String dateString = extractString("[^0-9]*(\\d{1,2} ... \\d{4})", "h1", doc);
		LocalDate meetingDate = DateTimeFormat.forPattern("dd MMM yyyy").parseLocalDate(dateString);
		//TODO Create this using a factory
		return new RacingPostMeeting(meetingDate.toString()+ course.toString(), course, meetingDate);
	}
	private Course getCourse(Document doc) {
		String courseName = extractString("(.*) Result", "h1", doc);
		
		//TODO Create this using a factory
		return new RacingPostCourse(null, courseName);
	}
	
	
	private Set<Integer> getRaceIdsOnDate(Calendar date) {
		Set<Integer> ids = new HashSet<>();
		Document doc = documentRetriever.getDocument("http://www.racingpost.com/horses2/results/home.sd?r_date="+dateFormat.format(date.getTime()));
		
		String expression = "race_id=(\\d+)";
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(doc.toString());
		while (matcher.find()) {
			ids.add(Integer.parseInt(matcher.group(1).trim()));
		}
		
		return ids;
	}

	private String extractString(String expression, String selector, Document doc) {
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(doc.select(selector).get(0).html());
		matcher.find();
		return Jsoup.parse(matcher.group(1).trim()).text();
	}

}
