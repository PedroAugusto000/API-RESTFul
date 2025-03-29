package com.workshop.carro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.workshop.carro.domain.Carro;
import com.workshop.carro.domain.dto.CarroDTO;

@Service // Agora não precisa mais dar um new CarroService na Controller, o spring já vai injetar a dependência pra ti
public class CarroService {
	
	@Autowired //Injeção de dependência
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros() {
		List<Carro> carros = rep.findAll();

		//Forma 1 expressão lambda: //List<CarroDTO> list = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
		//Forma 2 expressão lambda: //List<CarroDTO> list = carros.stream().map(CarroDTO::new).collect(Collectors.toList());
		
//Manualmente
//		List<CarroDTO> list = new ArrayList<>();
//		for (Carro c : carros) {
//			list.add(new CarroDTO(c));
//		}
//		return list;
		
		//Melhor código
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	}
	
	public Optional<CarroDTO> getCarroById(Long id) {
		return rep.findById(id).map(CarroDTO::create);
	}
	
	public List<CarroDTO> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public Carro insert(Carro carro) {
		return rep.save(carro);
	}

		public void delete(Long id) {
			if(getCarroById(id).isPresent()) {
				rep.deleteById(id);
			}
		}
}
