package com.workshop.carro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
	
	public Iterable<Carro> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}
	
	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Brasília"));
		carros.add(new Carro(3L, "Chevette"));
		
		return carros;
	}

	public Carro insert(Carro carro) {
		return rep.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");
		
		//Busca o carro no banco de dados
		Optional<Carro> optional = getCarroById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());
			
			// Atualiza o carro
			rep.save(db);
			
			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}

		//Poderia ser só o rep.deleteById, mas ai poderia deletar um objeto que não existe. O Hibernete e a JPA não conseguem deletar ele
		public Carro delete(Long id) {
			Optional<Carro> carro = getCarroById(id);
			if (carro.isPresent()) {
				rep.deleteById(id);
				return carro.get();
			} else {
				throw new RuntimeException("Carro não encontrado!");
			}
		}
}
