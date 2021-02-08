package com.test.backend.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.backend.DTO.Factura;
import com.test.backend.DTO.Pedido;
import com.test.backend.DTO.Producto;

@Component
public class PedidoDAO {

	private static  List<Pedido> pedidos = new ArrayList<>();
	private static int pedidosCuenta = 4;

	static {
		ProductoDAO productos = new ProductoDAO();
		List<Producto> productosPedido = new ArrayList<>();
		productosPedido.add(productos.findOne(1));
		pedidos.add(new Pedido(1, new Date(), productosPedido, 1, "ACEPTADO"));
		List<Producto> productosPedido1 = new ArrayList<>();
		productosPedido1.add(productos.findOne(3));
		productosPedido1.add(productos.findOne(2));
		productosPedido1.add(productos.findOne(5));
		pedidos.add(new Pedido(2, new Date(), productosPedido1, 4, "ACEPTADO"));
		List<Producto> productosPedido2 = new ArrayList<>();
		productosPedido2.add(productos.findOne(4));
		productosPedido2.add(productos.findOne(5));
		pedidos.add(new Pedido(3, new Date(), productosPedido2, 3, "ACEPTADO"));
		List<Producto> productosPedido3 = new ArrayList<>();
		productosPedido3.add(productos.findOne(6));
		Long tiempo12 = new Date().getTime()-46800000;
		pedidos.add(new Pedido(4, new Date(tiempo12), productosPedido3, 2, "ACEPTADO"));
	}

	public Pedido addPedido(Pedido pedido, int idCliente) {
		if(pedido.getId()==null)
			pedido.setId(++pedidosCuenta);
		Pedido nuevoPedido = new Pedido(pedido.getId(), new Date(), pedido.getProductos(), idCliente, "ACEPTADO");
		pedidos.add(nuevoPedido);
		confirmar(idCliente, nuevoPedido.getId());
		return nuevoPedido;
	}

	public List<Pedido> findPedidos(){
		return pedidos;
	}

	public Pedido findOne(int id) {
		for (Pedido pedido : pedidos) {
			if(pedido.getId()==id) 
				return pedido;
		}
		return null;
	}

	public List<Pedido> findPedidosCliente(int id){
		List<Pedido> pedidosCliente = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			if (pedido.getIdcliente()==id) 
				pedidosCliente.add(pedido);
		}
		return pedidosCliente;
	}

	public Pedido deleteOne(int idCliente, int idPedido) {
		Iterator<Pedido> iterator = pedidos.iterator();
		while (iterator.hasNext()) {
			Pedido pedido = (Pedido) iterator.next();
			if(pedido.getId() == idPedido && pedido.getIdcliente() == idCliente ) {
				if(tiempoPasado(pedido.getFecha()) > 12) {
					pedido.setEstado("CANCELADO");
					double nuevoValor = (pedido.getValor()*10)/100;
					pedido.setValor(nuevoValor);
				}
				else {
					iterator.remove();
					return pedido;
				}
			}
		}
		return null;
	}

	public Pedido update(int idCLiente, int idPedido, Pedido pedido) {
		Pedido updatePedido = findOne(idPedido);
		if (tiempoPasado(updatePedido.getFecha()) < 5) {
			if (pedido.getValor()>=updatePedido.getValor()) {
				updatePedido.setFecha(new Date());
				updatePedido.setProductos(pedido.getProductos());
				updatePedido.setValor();
				return updatePedido;
			}
		}
		return null;
	}

	public Pedido addProductos(int idCLiente, int idPedido, Producto producto) {
		Pedido updatePedido = findOne(idPedido);
		if(updatePedido.getIdcliente()==idCLiente) {
			updatePedido.getProductos().add(producto);
			updatePedido.setValor();
			confirmar(idCLiente, idPedido);
		}
		return updatePedido;
	}

	public Factura confirmar(int idCLiente, int idPedido) {
		Pedido pedido = findOne(idPedido);
		FacturaDAO facturas = new FacturaDAO();
		if(pedido.getIdcliente()==idCLiente) {
			Factura factura = facturas.findOnePedido(idPedido);
			if(factura==null) {
				factura = new Factura(null, idPedido, pedido.toString());
				facturas.addFactura(factura, idPedido);	
			}
			else {
				facturas.valorNuevo(factura.getId());
			}
			return factura;
		}
		return null;
	}

	public int tiempoPasado(Date fecha) {
		long in = fecha.getTime();
		long fin = new Date().getTime();
		Long diff= (((fin-in) / (1000*60*60)) % 24);
		return diff.intValue();
	}

}
