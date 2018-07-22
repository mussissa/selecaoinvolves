package com.involves.selecao.gateway.mongo;

import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoDbFactory {
	
	private MongoClient client;
	
	public MongoDbFactory() {
		client = MongoClients.create("${MONGODB_URI}");
	}
	
	public MongoDatabase getDb(){
		String dataBaseName = "heroku_6nqbgcqr";
		MongoDatabase database = client.getDatabase(dataBaseName);
		return database;
	}
}
