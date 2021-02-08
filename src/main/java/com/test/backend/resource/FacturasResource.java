package com.test.backend.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.backend.DAO.FacturaDAO;
import com.test.backend.DTO.Factura;

@RestController
public class FacturasResource {

	@Autowired
	private FacturaDAO DAO;

	/**
	 * Retorna las facturas
	 * @return List<Factura>
	 */
	@GetMapping("/facturas")
	public List<Factura> getFacturas(){
		return DAO.findFacturas();
	}
	
	/**
	 * Retorna factura por id
	 * @return Factura
	 */
	@GetMapping("/facturas/{id}")
	public Factura getFacturaId(@PathVariable int id) {
		return DAO.findOne(id);
	}

}
