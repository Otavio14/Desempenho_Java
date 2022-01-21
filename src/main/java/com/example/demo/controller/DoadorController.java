package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Doador;
import com.example.demo.repository.DoadorRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DoadorController {
	DoadorRepository repository;

	@GetMapping("/doador")
	public List<Doador> list() {
		return repository.findAll();
	}

	@PostMapping("/doador")
	public Doador register(@RequestBody Doador doador) {
		return repository.save(doador);
	}
}
