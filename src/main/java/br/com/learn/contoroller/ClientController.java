package br.com.learn.contoroller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.learn.models.Client;
import br.com.learn.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	ClientService clientService;

	@RequestMapping(method = RequestMethod.POST, value = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> cadastrarClient(@RequestBody Client client) {

		Client clienteCadastrado = clientService.cadastrar(client);

		return new ResponseEntity<Client>(clienteCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Client>> buscarClients() {

		Collection<Client> clientsBuscados = clientService.buscarClientsCadastrados();

		return new ResponseEntity<>(clientsBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")
	public ResponseEntity<Client> removerClients(@PathVariable Integer id) {

		Client client = clientService.buscarPorId(id);
		
		if (client==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		clientService.excluir(client);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> calterarClient(@RequestBody Client client) {

		Client clienteAlterado = clientService.alterar(client);

		return new ResponseEntity<Client>(clienteAlterado, HttpStatus.OK);
	}
	

	
}
