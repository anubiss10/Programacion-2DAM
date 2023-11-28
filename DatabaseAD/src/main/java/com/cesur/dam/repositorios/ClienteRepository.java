package com.cesur.dam.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesur.dam.Entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
