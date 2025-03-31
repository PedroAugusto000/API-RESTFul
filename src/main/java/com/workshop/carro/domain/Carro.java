package com.workshop.carro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

@Entity //(name = "carro") //Se o nome dar tabela for igual, igual no nosso caso, não precisa informar 
@Data
public class Carro {
	
	@Id //Chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento do ID
	private Long id;
	
	//Se lá no banco o nome for diferente: @Column(name = "nome")
	private String nome;
	private String tipo;
	private String descricao;
	private String urlFoto;
	private String urlVideo;
	private String latitude;
	private String longitude;
}
