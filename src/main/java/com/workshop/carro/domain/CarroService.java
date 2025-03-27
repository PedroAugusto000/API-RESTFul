package com.workshop.carro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.carro.domain.Carro;

@Service // Agora não precisa mais dar um new CarroService na Controller, o spring já vai injetar a dependência pra ti
public class CarroService {
	
	@Autowired //Injeção de dependência
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros() {
		return rep.findAll();
	}
	
	public Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}
	
	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasília"));
		carros.add(new Carro(3L, "Chevette"));
		
		return carros;
	}
}
