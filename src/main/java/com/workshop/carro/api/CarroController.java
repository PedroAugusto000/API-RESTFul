package com.workshop.carro.api;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.workshop.carro.domain.dto.CarroDTO;

@RestController
@RequestMapping ("/api/v1/carros") //Padrão bastante comum para criar API REST
public class CarroController {

	@Autowired //Informa com o Autowired
	private CarroService service;
	
	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros()); //Atalho p fazer a mesma coisa abaixo
		//return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional <CarroDTO> carro = service.getCarroById(id);
		
		//Expressão lambda
		return carro.map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());
		
		//Operador ternário
//		return carro.isPresent() ? 
//				ResponseEntity.ok(carro.get()) :
//				ResponseEntity.notFound().build();
	
		//Jeito raiz na mão chapa
//		if(carro.isPresent()) {
//			return ResponseEntity.ok(carro.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
	}	
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarroByTipo(tipo);
		
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}	
	
	@PostMapping
	public String post(@RequestBody Carro carro) { //RequestBody converte o json carro para o objeto carro
		Carro c = service.insert(carro);
		return "Carro  salvo com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
	    Carro c = service.delete(id);
	    return "Carro deletado com sucesso: " + c.getId();
	}
}
