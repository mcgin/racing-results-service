package com.gamblerstools.util.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gamblerstools.util.DocumentRetriever;

public class RetryingDocumentRetriever  implements DocumentRetriever {

	private int numberOfRetries;
	final static Logger logger = LoggerFactory.getLogger(RetryingDocumentRetriever.class);
	
	public RetryingDocumentRetriever (int numberOfRetries) {
		this.numberOfRetries = numberOfRetries;
	}
	@Override
	public Document getDocument(String url) {
		for (int attempts=0; attempts<numberOfRetries; attempts++) {
			try {
				return Jsoup.connect(url).get();
			} catch (IOException e) {
				logger.debug(e.getMessage()+" getting url: "+url+" attempt: "+attempts);
			}
		}
		logger.error("Failed to get url: "+url);
		
		return null;
	}

}
