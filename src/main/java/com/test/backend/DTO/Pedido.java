package com.test.backend.DTO;

import java.util.Date;
import java.util.List;

public class Pedido {

	private Integer id;
	private Date fecha;
	private List<Producto> productos;
	private Double valor;
	private Integer idcliente;
	private String estado;
	private Double valorDomicilio;
	
	public Pedido(Integer id, Date fecha, List<Producto> productos,  Integer idcliente, String estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.idcliente = idcliente;
		this.estado = estado;
		this.productos=productos;
		setValor();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public void setValor() {
		valor = 0.0;
		valorDomicilio = 3000.00;
		for (Producto producto : productos) {
			valor+=producto.getValor();
		}
		if(valor > 100000)
			valorDomicilio = 0.00;
	}
	public Integer getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getValorDomicilio() {
		return valorDomicilio;
	}
	public void setValorDomicilio(Double valorDomicilio) {
		this.valorDomicilio = valorDomicilio;
	}

	@Override
	public String toString() {
		return "Pedido [fecha=" + fecha + ", productos=" + productos 
				+ ", idcliente=" + idcliente + "]";
	}
}
