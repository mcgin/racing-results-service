package com.gamblerstools.results.service;

import com.gamblerstools.model.Course;

public interface CourseService {
	public Course findCourse(String courseName);
	public Course getCourse(String id);
}
