package com.test.backend.DAO;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.test.backend.DTO.Cliente;

@Component
public class ClienteDAO {

	private static  List<Cliente> clientes = new ArrayList<>();
	private static int clientesCuenta = 5;

	static {

		clientes.add(new Cliente(1, "Kevin Bavativa", "calle 2# 17-68", "16879"));
		clientes.add(new Cliente(2, "Luis Gonzales", "carrera 77# 128-12", "17598"));
		clientes.add(new Cliente(3, "Andres Soacha", "carrera 11# 14-08", "12345"));
		clientes.add(new Cliente(4, "Valeria Perez", "calle 1# 25-42", "12987"));
		clientes.add(new Cliente(5, "Diego GOmez", "carrera 22# 19-43", "16459"));
	}

	public Cliente addCliente(Cliente cliente) {
		if(cliente.getId()==null)
			cliente.setId(++clientesCuenta);

		clientes.add(cliente);
		return cliente;
	}

	public List<Cliente> findClientes(){
		return clientes;
	}

	public Cliente findOne(int id) {
		for (Cliente cliente : clientes) {
			if(cliente.getId()==id) 
				return cliente;
		}
		return null;
	}

	public Cliente deleteOne(int id) {
		Iterator<Cliente> iterator = clientes.iterator();
		while (iterator.hasNext()) {
			Cliente cliente = (Cliente) iterator.next();
			if(cliente.getId() == id) {
				iterator.remove();
				return cliente;
			}
		}
		return null;
	}
}
