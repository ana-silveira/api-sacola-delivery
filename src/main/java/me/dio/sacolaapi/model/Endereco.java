package me.dio.sacolaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@AllArgsConstructor // Construtor com todos os atributos será criado (Ver no Structure)
@Builder // Traz o componente Sacola Builder - AJuda na hora de criar esse objeto.
@Data // Cria todos os Getters e Setters
@Embeddable // A classe Endereco fica com Embeddable e as classes que usam o Endereço (Cliente, Restaurante etc) usam @Embedded
@NoArgsConstructor // COnstrutor criado sem argumentos (O Hibernate pede isso)- (Ver no Structure)


public class Endereco {
    private String cep;
    private String complemento;
}
