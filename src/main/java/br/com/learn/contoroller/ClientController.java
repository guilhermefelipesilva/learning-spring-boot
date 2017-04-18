package br.com.learn.contoroller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.learn.models.Client;

@RestController
public class ClientController {

	HashMap<Integer, Client> clients = new HashMap<>();
	Integer proximoId = 1;

	@RequestMapping(method = RequestMethod.POST, value = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> cadastrarClient(@RequestBody Client client) {

		Client clienteCadastrado = cadastrar(client);

		return new ResponseEntity<Client>(clienteCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Client>> buscarClients() {

		Collection<Client> clientsBuscados = buscarClientsCadastrados();

		return new ResponseEntity<>(clientsBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")
	public ResponseEntity<Client> removerClients(@PathVariable Integer id) {

		Client client = buscarPorId(id);
		
		if (client==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		excluir(client);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> calterarClient(@RequestBody Client client) {

		Client clienteAlterado = alterar(client);

		return new ResponseEntity<Client>(clienteAlterado, HttpStatus.OK);
	}
	
	private Client alterar(Client client){
		clients.put(client.getId(), client);
		return client;
	}

	private Client buscarPorId(Integer id) {
		return clients.get(id);
	}

	private void excluir(Client client) {
		clients.remove(client.getId());		
	}

	private Collection<Client> buscarClientsCadastrados() {

		return clients.values();
	}

	private Client cadastrar(Client client) {

		client.setId(proximoId);
		proximoId++;

		clients.put(client.getId(), client);

		return client;
	}
}
