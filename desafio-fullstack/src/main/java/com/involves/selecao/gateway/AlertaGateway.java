package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.alerta.Alerta;

import org.bson.types.ObjectId;

public interface AlertaGateway {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodos();
	
	public Alerta buscarPorObjetoId(ObjectId objeto); 

}
