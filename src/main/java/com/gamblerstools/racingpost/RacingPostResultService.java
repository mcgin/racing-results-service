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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gamblerstools.model.Course;
import com.gamblerstools.model.Meeting;
import com.gamblerstools.model.Race;
import com.gamblerstools.model.Result;
import com.gamblerstools.racingpost.model.RacingPostCourse;
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
		return null;
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
		//TODO Use a factory to create this
		return new RacingPostRace(meeting, raceTime, name);

	}
	private Meeting getMeeting(Document doc) {
		Course course = getCourse(doc);
		String dateString = extractString("[^0-9]*(\\d{1,2} ... \\d{4})", "h1", doc);
		LocalDate meetingDate = DateTimeFormat.forPattern("dd MMM yyyy").parseLocalDate(dateString);
		//TODO Create this using a factory
		return new RacingPostMeeting(course, meetingDate);
	}
	private Course getCourse(Document doc) {
		String courseName = extractString("(.*) Result", "h1", doc);
		
		//TODO Create this using a factory
		return new RacingPostCourse(courseName);
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
