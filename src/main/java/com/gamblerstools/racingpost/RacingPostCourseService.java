package com.gamblerstools.racingpost;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.gamblerstools.model.Course;

public class RacingPostCourseService {

	public Course findCourse(String courseString) {
		Map<String, String> data = new HashMap<>();
		data.put("search", courseString);
		data.put("category", "1");
		data.put("edition", "4");
		
		try {
			Document doc = Jsoup.connect("http://www.racingpost.com/public_gateway/db_search_interface.sd").data(data).parser(Parser.xmlParser()).post();
			System.out.println(doc.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
