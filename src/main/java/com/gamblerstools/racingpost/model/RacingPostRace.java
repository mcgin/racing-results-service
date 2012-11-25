package com.gamblerstools.racingpost.model;

import org.joda.time.DateTime;

import com.gamblerstools.model.Course;
import com.gamblerstools.model.Meeting;
import com.gamblerstools.model.Race;

public class RacingPostRace implements Race {

	private DateTime raceTime;
	private Course course;
	private Meeting meeting;
	private String name;

	public RacingPostRace(Course course, Meeting meeting, DateTime raceTime, String name) {
		this.course = course;
		this.meeting = meeting;
		this.raceTime = raceTime;
		this.name = name;
	}

	@Override
	public DateTime getRaceTime() {
		return raceTime;
	}

	@Override
	public Course getCourse() {
		return course;
	}

	@Override
	public Meeting getMeeting() {
		return meeting;
	}


	@Override
	public String toString() {
		return "RacingPostRace [raceTime=" + raceTime + ", course=" + course
				+ ", meeting=" + meeting + ", name=" + name + "]";
	}

	@Override
	public String getName() {
		return name;
	}
}
