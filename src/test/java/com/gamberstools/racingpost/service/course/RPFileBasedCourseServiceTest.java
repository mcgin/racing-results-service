package com.gamberstools.racingpost.service.course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gamblerstools.model.Course;
import com.gamblerstools.racingpost.service.course.RPFileBasedCourseService;
import com.gamblerstools.service.CourseService;

public class RPFileBasedCourseServiceTest {

	CourseService service;
	@Before
	public void setUp() throws Exception {
		service = new RPFileBasedCourseService();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findCourseByName_LingField_returnsLingFieldCourse() {
		Course course = service.findCourseByName("Lingfield");
		
		assertEquals("lingfield", course.getName().toLowerCase());
		assertEquals("31", course.getId());
	}
	@Test
	public void findCourseById_LingField_returnsLingFieldCourse() {
		Course course = service.findCourseById("31");
		
		assertEquals("lingfield", course.getName().toLowerCase());
		assertEquals("31", course.getId());
	}
	
	@Test
	public void findCourseByName_NotACourse_returnsNull() {
		Course course = service.findCourseByName("XYZ");
		assertNull(course);
	}
	@Test
	public void findCourseById_NotACourse_returnsNull() {
		Course course = service.findCourseById("XYZ");
		assertNull(course);
	}
	@Test
	public void findCourseByName_NullArgument_returnsNull() {
		Course course = service.findCourseByName(null);
		assertNull(course);
	}
	@Test
	public void findCourseById_NullArgument_returnsNull() {
		Course course = service.findCourseById(null);
		assertNull(course);
	}


}
