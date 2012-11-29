package com.gamblerstools.service;

import com.gamblerstools.model.Horse;

public interface HorseService {
	public Horse findHorseByName(String name);
	public Horse findHorseById(String Id);
}
