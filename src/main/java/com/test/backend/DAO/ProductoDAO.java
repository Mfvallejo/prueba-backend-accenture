package com.test.backend.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.backend.DTO.Producto;

@Component
public class ProductoDAO {

	private static  List<Producto> productos = new ArrayList<>();
	private static int productosCuenta = 6;
	
	static {
		productos.add(new Producto(1, "Telefono", 70000.00));
		productos.add(new Producto(2, "Microfono", 35000.00));
		productos.add(new Producto(3, "Camisa", 40000.00));
		productos.add(new Producto(4, "Pantalon", 55000.00));
		productos.add(new Producto(5, "Libro", 15000.00));
		productos.add(new Producto(6, "Camiseta", 55000.00));
	}
	
	public Producto addProducto(Producto producto) {
		if(producto.getId()==null)
			producto.setId(++productosCuenta);
		
		productos.add(producto);
		return producto;
	}
	
	public List<Producto> findProductos(){
		return productos;
	}
	
	public Producto findOne(int id) {
		for (Producto producto : productos) {
			if(producto.getId()==id) 
				return producto;
		}
		return null;
	}
	
	public Producto deleteOne(int id) {
		Iterator<Producto> iterator = productos.iterator();
		while (iterator.hasNext()) {
			Producto producto = (Producto) iterator.next();
			if(producto.getId() == id) {
				iterator.remove();
				return producto;
			}
		}
		return null;
	}
}
