package it.lamiapizzeria.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lamiapizzeria.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

} 
