package com.involves.selecao.gateway.mongo;

import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoDbFactory {
	
	private MongoClient client;
	
	public MongoDbFactory() {
		MongoClientURI mongoURI = new MongoClientURI(System.getenv("MONGODB_URI"));
		client = MongoClients.create(mongoURI+"");
	}
	
	public MongoDatabase getDb(){
		String dataBaseName = "selecao";
		MongoDatabase database = client.getDatabase(dataBaseName);
		return database;
	}
}
