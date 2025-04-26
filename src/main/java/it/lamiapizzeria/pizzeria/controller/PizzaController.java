package it.lamiapizzeria.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.lamiapizzeria.pizzeria.model.Pizza;
import it.lamiapizzeria.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping

public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping({"/", "/pizze"})
    public String index(Model model, @RequestParam(name="keyword", required=false) String nome) {
        List<Pizza> result;
        
        if(nome != null && !nome.isEmpty()){
            result=pizzaRepository.findByNomeContainingIgnoreCase(nome);
        }else {
            result = pizzaRepository.findAll();
        }
        model.addAttribute("pizze", result);
        model.addAttribute("keyword", nome);
        return "/pizze/index";
    }

    //Metodo per richiesta dettaglio pizze
    @GetMapping("/pizza/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);

        //Verifica se la pizza è presente
        if (pizzaOptional.isPresent()) {
            model.addAttribute("pizza", pizzaOptional.get());
            return "pizze/show";
        } else {
            model.addAttribute("errorMessage", "Oops! La pizza con ID " + id + " non esiste.");
            return "/error_pages/generic_error";
        }
    }

    //Mostro il form per creare una pizza in db
    @GetMapping("/pizza/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza()); //Passo oggetto vuoto al form
        return "pizze/create";
    }

    //Gestisco il salvataggio della nuova pizza(@PostMapping("path")
    @PostMapping("/pizza/create")
    public String storePizza(@Valid @ModelAttribute Pizza formPizza, 
        BindingResult bindingResult, 
        Model model,
        RedirectAttributes redirectAttributes){
            if(bindingResult.hasErrors()){
                return "/pizze/create";
            }

        pizzaRepository.save(formPizza); //Logica di salvataggio dei dati
        redirectAttributes.addFlashAttribute("successMessage", "Pizza creata con successo!");
        return "redirect:/pizze";//Dopo il salvataggio reinderizza nell'elenco
    }

    //Metodo per editare la pizza
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute("pizza", pizzaRepository.findById(id).get());

        return "/pizze/edit";
    }

    @PostMapping("/pizza/edit")
    public String update(@Valid @ModelAttribute Pizza formPizza, 
        BindingResult bindingResult, 
        Model model,
        RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "/pizze/edit";
        }

        pizzaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza modificata con successo!");
        return "redirect:/pizze";
    }

    //Cancellazione prodotto
    @PostMapping("/pizza/delete/{id}")
        public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
            pizzaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza eliminata con successo!");
        return "redirect:/pizze"; 
    }
}
