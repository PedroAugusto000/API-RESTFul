package com.workshop.carro.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //Todos os webservices do springboot devem ter essa anotação, transforma a classe em webservice rest
@RequestMapping ("/") //Vai estar mapeando para algum canto
public class IndexController {

	@GetMapping 
	public String get() { 
		return "API dos carros";
	}
}
