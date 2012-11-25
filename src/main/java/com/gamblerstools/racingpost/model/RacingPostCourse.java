package com.gamblerstools.racingpost.model;

import com.gamblerstools.model.Course;

public class RacingPostCourse implements Course {

	private String name;
	
	public RacingPostCourse(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "RacingPostCourse [name=" + name + "]";
	}

	
}
