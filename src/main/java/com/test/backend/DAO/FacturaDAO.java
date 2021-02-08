package com.test.backend.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.backend.DTO.Factura;
import com.test.backend.DTO.Pedido;

@Component
public class FacturaDAO {

	private static  List<Factura> facturas = new ArrayList<>();
	private static int facturaCuenta = 4;

	static {
		PedidoDAO pedidos = new PedidoDAO();
		facturas.add(new Factura(1,4,pedidos.findOne(4).toString()));
		facturas.add(new Factura(2,2,pedidos.findOne(2).toString()));
		facturas.add(new Factura(3,1,pedidos.findOne(1).toString()));
		facturas.add(new Factura(4,3,pedidos.findOne(3).toString()));
		for (Factura factura : facturas) {
			Pedido pedido = pedidos.findOne(factura.getIdPedido());
			if(factura.getIdPedido()==pedido.getId()) {
				factura.setValorNeto(pedido.getValor()+pedido.getValorDomicilio());
				Double d = (factura.getValorNeto()-(factura.getValorNeto()/1.19));
				Double iva = Math.round(d*100.0)/100.0;
				factura.setIva(iva);
			}
		}
	}

	public Factura addFactura(Factura factura, int idPedido) {
		if(factura.getId()==null) 
			factura.setId(++facturaCuenta);
		facturas.add(factura);
		valorNuevo(factura.getId());
		return factura;
	}

	public List<Factura> findFacturas(){
		return facturas;
	}

	public Factura findOne(int id) {
		for (Factura factura : facturas) {
			if(factura.getId()==id) 
				return factura;
		}
		return null;
	}

	public Factura findOnePedido(int id) {
		for (Factura factura : facturas) {
			if(factura.getIdPedido()==id) 
				return factura;
		}
		return null;
	}

	public void valorNuevo(int idFactura) {
		Factura factura = findOne(idFactura);
		PedidoDAO pedidos = new PedidoDAO();
		Pedido pedido = pedidos.findOne(factura.getIdPedido());
		factura.setValorNeto(pedido.getValor()+pedido.getValorDomicilio());
		Double d = (pedido.getValor()-(pedido.getValor()/1.19));
		Double iva = Math.round(d*100.0)/100.0;
		factura.setIva(iva);
		factura.setDescripcion(pedido.toString());
	}

}
