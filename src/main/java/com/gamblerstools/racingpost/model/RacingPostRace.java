package com.gamblerstools.racingpost.model;

import org.joda.time.DateTime;

import com.gamblerstools.model.Meeting;
import com.gamblerstools.model.Race;

public class RacingPostRace implements Race {

	private DateTime raceTime;
	private Meeting meeting;
	private String name;
	private String id;

	public RacingPostRace(String id, Meeting meeting, DateTime raceTime, String name) {
		this.meeting = meeting;
		this.raceTime = raceTime;
		this.name = name;
		this.id = id;
	}

	@Override
	public DateTime getRaceTime() {
		return raceTime;
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Meeting getMeeting() {
		return meeting;
	}



	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "RacingPostRace [raceTime=" + raceTime + ", meeting=" + meeting
				+ ", name=" + name + ", id=" + id + "]";
	}
}
