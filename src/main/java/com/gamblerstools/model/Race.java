package com.gamblerstools.model;

import org.joda.time.DateTime;

public interface Race {
	public DateTime getRaceTime();
	public Course getCourse();
	public Meeting getMeeting();
	public String getName();
}
