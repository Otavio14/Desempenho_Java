package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doador;

public interface DoadorRepository extends JpaRepository<Doador, String> {

}
