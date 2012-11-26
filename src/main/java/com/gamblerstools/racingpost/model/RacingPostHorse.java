package com.gamblerstools.racingpost.model;

import com.gamblerstools.model.Horse;

public class RacingPostHorse implements Horse {

	private String id;
	private String name;
	
	public RacingPostHorse(String id, String name) {
		this.name = name;
		this.id = id;
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "RacingPostHorse [id=" + id + ", name=" + name + "]";
	}
}
