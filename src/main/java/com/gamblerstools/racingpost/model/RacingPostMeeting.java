package com.gamblerstools.racingpost.model;

import java.util.Calendar;

import org.joda.time.LocalDate;

import com.gamblerstools.model.Course;
import com.gamblerstools.model.Meeting;

public class RacingPostMeeting implements Meeting{

	private LocalDate meetingDate;
	private Course course;
	public RacingPostMeeting(Course course, LocalDate meetingDate) {
		this.meetingDate = meetingDate;
		this.course = course;
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
	public String toString() {
		return "RacingPostMeeting [meetingDate=" + meetingDate + ", course="
				+ course + "]";
	}

}
