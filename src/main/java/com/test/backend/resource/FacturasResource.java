package com.test.backend.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.backend.DAO.FacturaDAO;
import com.test.backend.DTO.Factura;

@RestController
public class FacturasResource {

	@Autowired
	private FacturaDAO DAO;

	@GetMapping("/facturas")
	public List<Factura> getFacturas(){
		return DAO.findFacturas();
	}

}
