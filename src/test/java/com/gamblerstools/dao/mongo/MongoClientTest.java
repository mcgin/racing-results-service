package com.gamblerstools.dao.mongo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

public class MongoClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMongoConnection() {
		try {
			MongoClient mongoClient = new MongoClient("127.0.0.1", 27018);
			mongoClient.getDatabaseNames();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
