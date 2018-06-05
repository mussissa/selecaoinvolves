package com.involves.selecao.gateway;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class AlertaMongoGateway implements AlertaGateway{
	
	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = formato.format(new Date());
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		Document doc = new Document("ponto de venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", alerta.getFlTipo())
                .append("margem", alerta.getMargem())
				.append("produto", alerta.getProduto())
				.append("participacao", alerta.getParticipacao())
				.append("Data", data);
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodos() {
		
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		FindIterable<Document> db = collection.find();
		List<Alerta> alertas = new ArrayList<>();
		for (Document document : db) {
			Alerta alerta = new Alerta();
			alerta.setObjectId(document.getObjectId("_id"));
			alerta.setDescricao(document.getString("descricao"));
			alerta.setFlTipo(document.getInteger("tipo"));
			alerta.setMargem(document.getInteger("margem"));
			alerta.setPontoDeVenda(document.getString("ponto de venda"));
			alerta.setProduto(document.getString("produto"));
			alerta.setParticipacao(document.getInteger("participacao"));
			alerta.setData(document.getString("Data"));			
			alertas.add(alerta);
		}
		return alertas;
	}

	@Override
	public Alerta buscarPorObjetoId(ObjectId objeto) {
		
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		FindIterable<Document> db = collection.find(new Document().append("_id", objeto));
		Alerta alerta = new Alerta();
		for (Document document : db) {
			
			alerta.setObjectId(document.getObjectId("_id"));
			alerta.setDescricao(document.getString("descricao"));
			alerta.setFlTipo(document.getInteger("tipo"));
			alerta.setMargem(document.getInteger("margem"));
			alerta.setPontoDeVenda(document.getString("ponto de venda"));
			alerta.setProduto(document.getString("produto"));
			alerta.setParticipacao(document.getInteger("participacao"));
			alerta.setData(document.getString("Data"));			
			
		}
		return alerta;
	}


}
