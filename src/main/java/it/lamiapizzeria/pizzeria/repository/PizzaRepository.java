package it.lamiapizzeria.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lamiapizzeria.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

    public List<Pizza> findByNomeContainingIgnoreCase(String nome);
    /*
     * Questo metodo cerca tutte le pizze il cui nome contiene la stringa passata come parametro.
        Spring crea automaticamente la query SQL dietro le quinte, quindi:

🔍      findByNameContaining("mar") troverà:

            Margherita

            Parmigiana

            Amatriciana
            */
} 

//Un repository fornisce metodi per recuperare, salvare, 
//aggiornare ed eliminare dati dalla sorgente di dati persistente, 
//come un database. Questa interazione avviene attraverso 
//le operazioni CRUD (Create, Read, Update, Delete) che il repository offre.
