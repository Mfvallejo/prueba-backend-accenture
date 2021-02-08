
package com.test.backend.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.backend.DAO.PedidoDAO;
import com.test.backend.DTO.Pedido;
import com.test.backend.DTO.Producto;

@RestController
public class PedidoResource {

	@Autowired
	private PedidoDAO DAO;

	/**
	 * Retorna todos los pedidos
	 * @return List<Pedido>
	 */
	@GetMapping("/pedidos")
	public List<Pedido> getPedidos(){
		return DAO.findPedidos();
	}

	/**
	 * Retorna los peididos por cliente
	 * @return List<Pedido>
	 */
	@GetMapping("/clientes/{id}/pedidos")
	public List<Pedido> getPedidosCliente(@PathVariable int id) {
		return DAO.findPedidosCliente(id);
	}

	/**
	 * Agrega un pedido de un cliente
	 */
	@PostMapping("/clientes/{id}/pedidos")
	public ResponseEntity<Object> addPedidosCliente(@PathVariable int id, @RequestBody Pedido pedido){
		DAO.addPedido(pedido,id);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * Agrega productos a un pedido de un cliente
	 */
	@PostMapping("/clientes/{id}/pedidos/{idPedido}")
	public Pedido addProductosPedido(@PathVariable int id, @PathVariable int idPedido, @RequestBody Producto productos){
		return DAO.addProductos(id, idPedido, productos);
	}

	/**
	 * Modifica el pedido de un cliente
	 */
	@PutMapping("/clientes/{id}/pedidos/{idPedido}")
	public Pedido updatePedido(@PathVariable int id, @PathVariable int idPedido, @RequestBody Pedido pedido){
		return DAO.update(id, idPedido, pedido);
	}
	
	/**
	 * Elimina el pedido de un cliente
	 */
	@DeleteMapping("/clientes/{id}/pedidos/{idPedido}")
	public void deletePedidosCliente(@PathVariable int id, @PathVariable int idPedido) {
		DAO.deleteOne(id, idPedido);
	}
}
