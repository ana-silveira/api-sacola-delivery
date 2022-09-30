package me.dio.sacolaapi.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor // Construtor com todos os atributos será criado (Ver no Structure)
@Builder
@Data // Cria todos os Getters e Setters
@Embeddable
@NoArgsConstructor // COnstrutor criado sem argumentos (O Hibernate pede isso)- (Ver no Structure)

public class ItemDto {
    private Long produtoId;
    private int quantidade;
    private Long sacolaId;
}
