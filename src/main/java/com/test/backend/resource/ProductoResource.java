package com.test.backend.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.backend.DAO.ProductoDAO;
import com.test.backend.DTO.Producto;

@RestController
public class ProductoResource {

	@Autowired
	private ProductoDAO DAO;

	/**
	 * Retorna los productos
	 * @return List<Producto>
	 */
	@GetMapping("/productos")
	public List<Producto> getProductos(){
		return DAO.findProductos();
	}

	/**
	 * Retorna el producto por id
	 * @return Producto
	 */
	@GetMapping("/productos/{id}")
	public Producto getProductoId(@PathVariable int id) {
		return DAO.findOne(id);
	}

	/**
	 * Agrega un producto
	 */
	@PostMapping("/productos")
	public ResponseEntity<Object> addProducto(@RequestBody Producto producto){
		DAO.addProducto(producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(producto.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
