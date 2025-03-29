package com.workshop.carro.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//Nossa interface que vai se comunicar com o banco de dados
public interface CarroRepository extends CrudRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);
}
