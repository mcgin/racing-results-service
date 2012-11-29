package com.gamblerstools.racingpost.service.course;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.gamblerstools.model.Course;
import com.gamblerstools.racingpost.model.RacingPostCourse;
import com.gamblerstools.service.CourseService;

public class RPFileBasedCourseService implements CourseService {
	final static Properties coursesById;
	final static Map<String, String> coursesByName;
    
	static {
	    coursesById = new Properties();
	    InputStream stream = RPFileBasedCourseService.class.getResourceAsStream("/racingpost/courses"); // open the file
	    try {
	    	stream.available();
			coursesById.load(stream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    coursesByName = new HashMap<>();
	    for(Object key:coursesById.keySet()){
	    	coursesByName.put(coursesById.getProperty(key.toString()),key.toString());
	    }
	}
	
	@Override
	public Course findCourseByName(String courseName) {
		String courseId = coursesByName.get(courseName);
		return courseId == null ? null : new RacingPostCourse(courseId, courseName);
	}

	@Override
	public Course findCourseById(String id) {
		Course course;
		try {
			String courseName = coursesById.get(id).toString();
			course = courseName == null ? null : new RacingPostCourse(id, courseName);
		} catch (NullPointerException npe) {
			course = null;
		}
		return course;
	}

}
