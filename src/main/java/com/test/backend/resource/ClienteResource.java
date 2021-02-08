package com.test.backend.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.backend.DAO.ClienteDAO;
import com.test.backend.DTO.Cliente;

@RestController
public class ClienteResource {

	@Autowired
	private ClienteDAO DAO;


	@GetMapping("/clientes")
	public List<Cliente> getClientes(){
		return DAO.findClientes();
	}

	@GetMapping("/clientes/{id}")
	public Cliente getClienteId(@PathVariable int id) {
		return DAO.findOne(id);

	}

	@PostMapping("/clientes")
	public ResponseEntity<Object> addCliente(@RequestBody Cliente cliente){
		DAO.addCliente(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/clientes/{id}")
	public void deleteCLiente(@PathVariable int id) {
		Cliente cliente = DAO.deleteOne(id);
		if(cliente==null) {

		}
	}

}
