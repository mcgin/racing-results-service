package com.gamblerstools.racingpost.service.horse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.gamblerstools.model.Horse;
import com.gamblerstools.service.HorseService;

@Category(com.gamblerstools.test.category.IntegrationTest.class)
public class RPRemoteHorseServiceTest {

	HorseService service;
	@Before
	public void setUp() throws Exception {
		service = new RPRemoteHorseService();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findHorseByName_SingleHorseInSearchIstabraq_returnsIstabraqHorse() {
		Horse horse = service.findHorseByName("Istabraq");
		
		assertEquals("Istabraq", horse.getName());
		assertEquals("90480", horse.getId());
	}
	@Test
	public void findHorseByName_MultipleHorseInSearchDeadly_returnsDeadlyHorse() {
		Horse horse = service.findHorseByName("Deadly");
		
		assertEquals("Deadly", horse.getName());
		assertEquals("678410", horse.getId());
	}
	@Test
	public void findHorseById_Istabraq_returnsIstabraqCourse() {
		Horse horse = service.findHorseById("90480");
		
		assertEquals("Istabraq", horse.getName());
		assertEquals("90480", horse.getId());
	}
	
	@Test
	public void findHorse_NotAHorse_returnsNull() {
		Horse horse = service.findHorseByName("1XYZ");
		assertNull(horse);
	}
}
