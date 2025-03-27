package com.workshop.carro.domain;

import org.springframework.data.repository.CrudRepository;

//Nossa interface que vai se comunicar com o banco de dados
public interface CarroRepository extends CrudRepository<Carro, Long> {

}
