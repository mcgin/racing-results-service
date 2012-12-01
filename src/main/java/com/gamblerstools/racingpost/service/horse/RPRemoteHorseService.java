package com.gamblerstools.racingpost.service.horse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.gamblerstools.model.Course;
import com.gamblerstools.model.Horse;
import com.gamblerstools.racingpost.model.RacingPostCourse;
import com.gamblerstools.racingpost.model.RacingPostHorse;
import com.gamblerstools.service.HorseService;

public class RPRemoteHorseService implements HorseService {

	@Override
	public Horse findHorseByName(String horseName) {
		Map<String, String> data = new HashMap<>();
		data.put("search", horseName);
		data.put("category", "2");
		data.put("edition", "4");
		/**
		 * <item> <DETAIL>IRE</DETAIL> <START_DATE>1992</START_DATE>
		 * <ID>90480</ID> <MISC></MISC> <NAME>Istabraq</NAME>
		 * <STYLE_NAME>Istabraq (IRE) - 1992</STYLE_NAME> </item>
		 */
		try {
			Document doc = Jsoup
					.connect(
							"http://www.racingpost.com/public_gateway/db_search_interface.sd")
					.data(data).parser(Parser.xmlParser()).post();
			Elements courseElements = doc.select("item");
			for (Element e : courseElements) {
				if (e.select("name").text().equalsIgnoreCase(horseName)) {
					return parseHorseFromItem(e);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Horse findHorseById(String id) {
		// TODO Auto-generated method stub
		// http://www.racingpost.com/horses/horse_home.sd?horse_id=90480
		try {
			Document doc = Jsoup.connect(
					"http://www.racingpost.com/horses/horse_home.sd?horse_id="
							+ id).get();
			Elements courseElements = doc.select("title");
			Pattern pattern = Pattern.compile("www.horse.horseId = (\\d+)");
			Matcher matcher = pattern.matcher(doc.toString());
			matcher.find();

			for (Element e : courseElements) {
				String horseName = e.text().replace(" | Racing Post", "");
				String horseId = matcher.group(1);
				return new RacingPostHorse(horseId, horseName);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Horse parseHorseFromItem(Element e) {
		return new RacingPostHorse(e.select("id").text(), e.select("name")
				.text());

	}
}
