package com.gamblerstools.results.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gamblerstools.racingpost.RacingPostCourseService;

public class RacingPostCourseServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		RacingPostCourseService service = new RacingPostCourseService();
		service.findCourse("Lingfield");
	}

}
