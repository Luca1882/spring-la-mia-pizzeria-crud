package it.lamiapizzeria.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.lamiapizzeria.pizzeria.model.Pizza;
import it.lamiapizzeria.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping

public class PizzaController{

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model){
        List<Pizza> result = pizzaRepository.findAll();
        model.addAttribute("pizze", result );
        return "index";
    }
    
}
    

