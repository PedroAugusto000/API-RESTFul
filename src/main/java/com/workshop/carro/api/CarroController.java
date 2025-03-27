package com.workshop.carro.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.carro.domain.Carro;
import com.workshop.carro.domain.CarroService;

@RestController
@RequestMapping ("/api/v1/carros") //Padrão bastante comum para criar API REST
public class CarroController {

	@Autowired //Informa com o Autowired
	private CarroService service;
	
	@GetMapping()
	public Iterable<Carro> get() {
		return service.getCarros();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return service.getCarroById(id);
	}	
}
