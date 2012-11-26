package com.gamblerstools.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gamblerstools.racingpost.model.RacingPostFinishingPosition;

public class FinishingPositionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void twoArgConstructor_finishedZeroPosition_exception () {
	                new RacingPostFinishingPosition(RaceOutcome.FINISHED, 0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void twoArgConstructor_finishedNegativePosition_exception () {
	                new RacingPostFinishingPosition(RaceOutcome.FINISHED, -1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void oneArgConstructor_finishedNoPosition_exception () {
	                new RacingPostFinishingPosition(RaceOutcome.FINISHED);
	}
	@Test(expected=IllegalArgumentException.class)
	public void twoArgConstructor_finishedAndNullPosition_exception () {
	                new RacingPostFinishingPosition(RaceOutcome.FINISHED, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void twoArgConstructor_pulledupAndNotNullPosition_exception () {
	                new RacingPostFinishingPosition(RaceOutcome.PULLED_UP, 1);
	}
	 
	@Test
	public void compareTo_twoFinishedHorses_1lt2() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 1);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 2);
	                assertTrue(pos1.compareTo(pos2)<0);
	                assertTrue(pos2.compareTo(pos1)>0);
	}
	 
	@Test
	public void compareTo_twoFinishedHorses_1lt10() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 1);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 10);
	                assertTrue(pos1.compareTo(pos2)<0);
	                assertTrue(pos2.compareTo(pos1)>0);
	}
	 
	@Test
	public void compareTo_twoFinishedHorses_3lt10() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 3);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 10);
	                assertTrue(pos1.compareTo(pos2)<0);
	                assertTrue(pos2.compareTo(pos1)>0);
	}
	 
	@Test
	public void compareTo_compareTheSameFinishPosition_isZero() {
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 2);
	                assertTrue(pos2.compareTo(pos2)==0);
	}
	 
	@Test
	public void compareTo_pulledupHorseWithSelf_isZero() {
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.PULLED_UP);
	                assertTrue(pos2.compareTo(pos2)==0);
	}
	 
	@Test
	public void compareTo_fallenHorseWithSelf_isZero() {
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FELL);
	                assertTrue(pos2.compareTo(pos2)==0);
	}
	 
	@Test
	public void compareTo_twoFallenHorses_isZero() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.FELL);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FELL);
	                assertTrue(pos1.compareTo(pos2)==0);
	                assertTrue(pos2.compareTo(pos1)==0);
	}
	@Test
	public void compareTo_twoPulledUpHorses_isZero() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.PULLED_UP);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.PULLED_UP);
	                assertTrue(pos1.compareTo(pos2)==0);
	                assertTrue(pos2.compareTo(pos1)==0);
	}
	 
	@Test
	public void compareTo_oneFinishedHorseOnePulledUp_finishedLTPulledUp() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 3);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.PULLED_UP);
	                assertTrue(pos1.compareTo(pos2)<0);
	                assertTrue(pos2.compareTo(pos1)>0);
	}
	 
	@Test
	public void compareTo_oneFinishedHorseOneFell_finishedLTFell() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.FINISHED, 3);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FELL);
	                assertTrue(pos1.compareTo(pos2)<0);
	                assertTrue(pos2.compareTo(pos1)>0);
	}
	 
	@Test
	public void compareTo_onePulledUpHorseOnePulledUp_isZero() {
	                FinishingPosition pos1 = new RacingPostFinishingPosition(RaceOutcome.PULLED_UP);
	                FinishingPosition pos2 = new RacingPostFinishingPosition(RaceOutcome.FELL);
	                assertTrue(pos1.compareTo(pos2)==0);
	                assertTrue(pos2.compareTo(pos1)==0);
	}

}
