package com.gamblerstools.racingpost.service.course;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.gamblerstools.model.Course;
import com.gamblerstools.racingpost.service.course.RPRemoteCourseService;
import com.gamblerstools.service.CourseService;

@Category(com.gamblerstools.test.category.IntegrationTest.class)
public class RPRemoteCourseServiceTest {
	CourseService service;
	@Before
	public void setUp() throws Exception {
		service = new RPRemoteCourseService();
		
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
	public void findCourse_NotACourse_returnsNull() {
		Course course = service.findCourseByName("XYZ");
		assertNull(course);
	}

}
