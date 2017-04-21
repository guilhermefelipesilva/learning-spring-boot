package br.com.learn.service;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import br.com.learn.models.Client;

@Service
public class ClientService {
	
	private HashMap<Integer, Client> clients = new HashMap<>();
	private Integer proximoId = 1;
	
	public Client buscarPorId(Integer id) {
		return clients.get(id);
	}

	public void excluir(Client client) {
		clients.remove(client.getId());		
	}

	public Collection<Client> buscarClientsCadastrados() {

		return clients.values();
	}

	public Client cadastrar(Client client) {

		client.setId(proximoId);
		proximoId++;

		clients.put(client.getId(), client);

		return client;
	}
	
	public Client alterar(Client client){
		clients.put(client.getId(), client);
		return client;
	}

}
