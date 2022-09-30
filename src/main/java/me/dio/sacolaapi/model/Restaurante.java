package me.dio.sacolaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor // Construtor com todos os atributos será criado (Ver no Structure)
@Builder // Traz o componente Sacola Builder - AJuda na hora de criar esse objeto.
@Data // Cria todos os Getters e Setters
@Entity   // Notação que Vai dizer que essa classe vai virar uma tabela no banco.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Devido à fetch LAZY que as vezes dá erro no console quando o JSON e o Hibernate estão rodando juntos.
@NoArgsConstructor // COnstrutor criado sem argumentos (O Hibernate pede isso)- (Ver no Structure)


public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL) // Um restaurante com varios produtos; O cascade é: se eu excluo o restaurante, tbm excluo todos os produtos
    private List<Produto> cardapio;
    @Embedded
    private Endereco endereco;
}
