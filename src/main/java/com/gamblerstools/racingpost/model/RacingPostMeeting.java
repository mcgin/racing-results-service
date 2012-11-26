package com.gamblerstools.racingpost.model;

import org.joda.time.LocalDate;

import com.gamblerstools.model.Course;
import com.gamblerstools.model.Meeting;

public class RacingPostMeeting implements Meeting{

	private LocalDate meetingDate;
	private Course course;
	private String id;
	
	public RacingPostMeeting(String id, Course course, LocalDate meetingDate) {
		this.meetingDate = meetingDate;
		this.course = course;
		this.id = id;
	}
	@Override
	public LocalDate getMeetingDate() {
		return meetingDate;
	}
	@Override
	public Course getCourse() {
		return course;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "RacingPostMeeting [meetingDate=" + meetingDate + ", course="
				+ course + ", id=" + id + "]";
	}

}
