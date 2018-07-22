package com.involves.selecao.gateway.mongo;

import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
@PropertySource({ "classpath:application.properties" })
public class MongoDbFactory {
	
	private MongoClient client;
	
	public MongoDbFactory() {
		client = MongoClients.create();
	}
	
	public MongoDatabase getDb(){
		String dataBaseName = "heroku_pj1z4pbh";
		MongoDatabase database = client.getDatabase(dataBaseName);
		return database;
	}
}
