package com.involves.selecao.alerta;

import org.bson.types.ObjectId;

public class Alerta {

	private ObjectId id;
	private String pontoDeVenda;
	private String descricao;
	private String produto;
	private Integer flTipo;
	private Integer margem;
	private Integer participacao;
	private String  data;
	
	
	
		
	public ObjectId getObjectId() {
		return id;
	}
	public void setObjectId(ObjectId id) {
		this.id = id;
	}

	public String getPontoDeVenda() {
		return pontoDeVenda;
	}
	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Integer getFlTipo() {
		return flTipo;
	}
	public void setFlTipo(Integer flTipo) {
		this.flTipo = flTipo;
	}
	public Integer getMargem(){
		return margem;
	}
	public void setMargem(Integer margem){
		this.margem = margem;
	}

	public Integer getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Integer participacao) {
		this.participacao = participacao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	

}
