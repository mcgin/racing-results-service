package com.gamblerstools.racingpost.model;

import com.gamblerstools.model.Course;

public class RacingPostCourse implements Course {

	private String name;
	private String id;
	
	public RacingPostCourse(String id, String name) {
		this.name = name;
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "RacingPostCourse [name=" + name + ", id=" + id + "]";
	}

	
}
