package com.gamblerstools.results.service;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gamblerstools.racingpost.RacingPostResultService;
import com.gamblerstools.util.impl.FakeDocumentRetriever;
import com.gamblerstools.util.impl.SimpleDocumentRetriever;

public class ResultServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ResultService resultService = new RacingPostResultService(new FakeDocumentRetriever());
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -1);
		assertTrue(resultService.getResults(date).size()>0);
	}

}
