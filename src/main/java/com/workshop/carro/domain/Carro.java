package com.workshop.carro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //(name = "carro") //Se o nome dar tabela for igual, igual no nosso caso, não precisa informar 
public class Carro {
	
	@Id //Chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento do ID
	private Long id;
	
	//Se lá no banco o nome for diferente: @Column(name = "nome")
	private String nome;
	
	private String tipo;
	
	public Carro() {}
	
	public Carro(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
