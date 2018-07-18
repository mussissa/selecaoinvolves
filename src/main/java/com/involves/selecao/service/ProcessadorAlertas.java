package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws IOException {
		
		boolean redirect = false;
		String inputLine;
		
		URL url = new URL("http://selecao-involves.agilepromoter.com/pesquisas");
		        
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		
		con.connect();
		
		int responsecode = con.getResponseCode();
	
		if (responsecode == HttpURLConnection.HTTP_MOVED_PERM){
            redirect = true;
		}
		
		if (redirect){
            
           String endereco = con.getHeaderField("Location");
           
           URL novaUrl = new URL(endereco);
           
           con = (HttpURLConnection) novaUrl.openConnection();
            
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
            
		    content.append(inputLine);
		}
		in.close();
		
		Gson gson = new Gson();

		Pesquisa[] ps =gson.fromJson(content.toString(), Pesquisa[].class);
		
		for (int i = 0; i < ps.length; i++){
			for (int j = 0; j < ps[i].getRespostas().size(); j++){
				Resposta resposta = ps[i].getRespostas().get(j);
				
				if (resposta.getPergunta().equals("Qual a situação do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
					
					    Alerta alerta = new Alerta();
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setDescricao("Ruptura detectada!");
					    alerta.setProduto(ps[i].getProduto());
						alerta.setFlTipo(1);
					    gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(ps[i].getPreco_estipulado());
					
					if(precoColetado > precoEstipulado){
					    Alerta alerta = new Alerta();
					    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
					    alerta.setMargem(margem);
					    alerta.setDescricao("Preço acima do estipulado!");
					    alerta.setProduto(ps[i].getProduto());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(2);
					    gateway.salvar(alerta);
					} else if(precoColetado < precoEstipulado){
						Alerta alerta = new Alerta();
					    int margem = Integer.parseInt(resposta.getResposta()) - precoEstipulado ;
					    alerta.setMargem(margem);
					    alerta.setDescricao("Preço abaixo do estipulado!");
						alerta.setProduto(ps[i].getProduto());
						
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(3);
					    gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equalsIgnoreCase("%Share")){
					int shareColetado = Integer.parseInt(resposta.getResposta());
					int shareEstipulado = Integer.parseInt(ps[i].getParticipacao_estipulada());
					
					if(shareColetado < shareEstipulado){
						Alerta alerta = new Alerta();
						int margem = shareEstipulado - shareColetado;
						alerta.setMargem(margem);
						alerta.setDescricao("Participação inferior ao estipulado");
						alerta.setProduto(ps[i].getCategoria());
						alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
						alerta.setParticipacao(shareColetado);
						alerta.setFlTipo(4);
						gateway.salvar(alerta);
					}else{
						Alerta alerta = new Alerta();
						int margem = shareColetado - shareEstipulado;
						alerta.setMargem(margem);
						alerta.setDescricao("Participação superior ao estipulado");
						alerta.setProduto(ps[i].getCategoria());
						alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
						alerta.setParticipacao(shareColetado);
						alerta.setFlTipo(5);
						gateway.salvar(alerta);
						
					}
					
				}
				
			} 
		}
	
	}
}

