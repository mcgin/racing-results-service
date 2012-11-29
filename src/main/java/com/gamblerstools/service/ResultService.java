package com.gamblerstools.service;

import java.util.Calendar;
import java.util.List;

import com.gamblerstools.model.Result;

public interface ResultService {
	public List<Result> getResults(Calendar date);
}
