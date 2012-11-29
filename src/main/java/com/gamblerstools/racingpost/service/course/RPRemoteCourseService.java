package com.gamblerstools.racingpost.service.course;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.gamblerstools.model.Course;
import com.gamblerstools.racingpost.model.RacingPostCourse;
import com.gamblerstools.service.CourseService;

public class RPRemoteCourseService implements CourseService {


	@Override
	public Course findCourseByName(String courseString) {
		Map<String, String> data = new HashMap<>();
		data.put("search", courseString);
		data.put("category", "1");
		data.put("edition", "4");
		
		try {
			Document doc = Jsoup.connect("http://www.racingpost.com/public_gateway/db_search_interface.sd").data(data).parser(Parser.xmlParser()).post();
			Elements courseElements = doc.select("item");
			for(Element e:courseElements) {
				if(e.select("name").text().equalsIgnoreCase(courseString)) {
					return parseCourseFromItem(e);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Course findCourseById(String id) {
		// http://www.racingpost.com/horses/course_home.sd?crs_id=36
		try {
			Document doc = Jsoup.connect("http://www.racingpost.com/horses/course_home.sd?crs_id="+id).get();
			Elements courseElements = doc.select("h1");
			for(Element e:courseElements) {
				if(e.text().contains(" Racecourse")) {
					String courseName = e.text().replace(" Racecourse", "");
					String courseId = doc.select("input[id=crs_id]").first().attr("value");
					return new RacingPostCourse(courseId, courseName);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private Course parseCourseFromItem(Element e) {
		/**
		 *   <item> 
			   <detail>GB</detail> 
			   <start_date>0</start_date>
			   <id>31</id>
			   <misc>LIN</misc>
			   <name>LINGFIELD</name>
			   <style_name>LINGFIELD (GB)</style_name> 
			  </item> 
		 */
		return new RacingPostCourse(e.select("id").text(), e.select("name").text());
	}

	
	
}
