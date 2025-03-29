package com.workshop.carro.domain.dto;

import org.modelmapper.ModelMapper;

import com.workshop.carro.domain.Carro;

import lombok.*;

@Data
public class CarroDTO {

	private Long id;
	private String nome;
	private String tipo;
	
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
}
