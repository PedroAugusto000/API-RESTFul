package com.workshop.carro.domain;

import java.util.ArrayList;
import java.util.List;

import com.workshop.carro.domain.Carro;

public class CarroService {
	
	public List<Carro> getCarros() {
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasília"));
		carros.add(new Carro(3L, "Chevette"));
		
		return carros;
	}
}
