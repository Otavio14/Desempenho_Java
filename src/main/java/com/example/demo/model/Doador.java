package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doador {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	Long id;
	@Id
	String cpf;
	String nome;
	String data_nasc;
	String email;
	String telefone;
	String cep;
	String logradouro;
	String complemento;
	String bairro;
	String cidade;
	String uf;
	String numero;
	String senha;
	String codigo;
	String auth;
}
