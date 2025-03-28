package com.workshop.carro.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		return service.getCarroByTipo(tipo);
	}	
	
	@PostMapping
	public String post(@RequestBody Carro carro) { //RequestBody converte o json carro para o objeto carro
		Carro c = service.insert(carro);
		return "Carro  salvo com sucesso: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id")Long id, @RequestBody Carro carro) { 
		
		Carro c = service.update(carro, id);
		
		return "Carro  atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
	    Carro c = service.delete(id);
	    return "Carro deletado com sucesso: " + c.getId();
	}
}
