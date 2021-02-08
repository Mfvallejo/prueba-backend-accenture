package com.test.backend.DTO;

public class Cliente {
	
	private Integer id;
	private String nombre;
	private String direccion;
	private String cedula;

	
	public Cliente(Integer id, String nombre, String direccion, String cedula) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.cedula = cedula;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", cedula=" + cedula + "]";
	}
}
