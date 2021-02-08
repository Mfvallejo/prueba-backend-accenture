package com.test.backend.DTO;

public class Factura {

	private Integer id;
	private Integer idPedido;
	private String descripcion;
	private Double valorNeto;
	private Double iva;
	
	
	public Factura(Integer id, Integer idPedido, String descripcion) {
		super();
		this.id = id;
		this.idPedido = idPedido;
		this.descripcion = descripcion;
		this.valorNeto = 0.00;
		this.iva = 0.00;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValorNeto() {
		return valorNeto;
	}
	public void setValorNeto(Double valorNeto) {
		this.valorNeto = valorNeto;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", idPedido=" + idPedido + ", descripcion=" + descripcion + ", valorNeto="
				+ valorNeto + ", iva=" + iva + "]";
	} 
}
