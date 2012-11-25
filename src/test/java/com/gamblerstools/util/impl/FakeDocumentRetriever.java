package com.gamblerstools.util.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gamblerstools.util.DocumentRetriever;

public class FakeDocumentRetriever implements DocumentRetriever{

	@Override
	public Document getDocument(String url) {
		//Results page for the day
		//http://www.racingpost.com/horses2/results/home.sd?r_date=
		//Results page for a race
		//http://www.racingpost.com/horses/result_home.sd?race_id=
		String file = "";
		if(url.contains("http://www.racingpost.com/horses2/results/home.sd?r_date=")) {
			file = "/horse2Results.html";
		} else if (url.contains("http://www.racingpost.com/horses/result_home.sd?race_id=")) {
			file = "/horses.html";
		}
		
		try {
			return Jsoup.parse(this.getClass().getResourceAsStream(file), "UTF-8", "http://example.com/");
		} catch (IOException e) {
			throw new RuntimeException("No file found for url: "+url,e);
		}
		
	}

	
	
	

}
