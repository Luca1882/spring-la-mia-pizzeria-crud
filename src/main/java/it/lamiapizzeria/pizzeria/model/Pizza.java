package it.lamiapizzeria.pizzeria.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="pizze")

public class Pizza{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "nome", nullable = false, length = 100) // colonna obbligatoria nel DB, max 100 caratteri
    @NotBlank(message = "Il nome è obbligatorio") // validazione lato form: non può essere vuoto o solo spazi
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri")
    private String nome;

    @Column(name = "descrizione", nullable = false, length = 100) //colonna nel db con max 100 caratteri
    @NotBlank(message = "La descrizione è obbligatoria")
    @Size(max = 200, message = "La descrizione non può superare i 200 caratteri")
    private String descrizione;

    @NotBlank(message = "L' URL è obbligatorio")
    @Column(name = "foto_url", nullable = false)
    @Pattern(regexp = "^https?://.*", message = "Inserisci un URL valido")
    private String foto_url;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    @Column(nullable = false)
    private BigDecimal prezzo;

    //Getter Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome; 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}