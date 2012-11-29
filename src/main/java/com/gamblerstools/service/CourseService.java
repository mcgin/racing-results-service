package com.gamblerstools.service;

import com.gamblerstools.model.Course;

public interface CourseService {
	public Course findCourseByName(String courseName);
	public Course findCourseById(String id);
}
