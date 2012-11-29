package com.gamblerstools.results.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gamblerstools.racingpost.service.course.RPRemoteCourseService;

public class RacingPostCourseServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		RPRemoteCourseService service = new RPRemoteCourseService();
		service.findCourseByName("Lingfield");
	}

}
