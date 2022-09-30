package me.dio.sacolaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.sacolaapi.enumeration.FormaPagamento;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor // Construtor com todos os atributos será criado (Ver no Structure)
@Builder // Traz o componente Sacola Builder - AJuda na hora de criar esse objeto.
@Data // Cria todos os Getters e Setters
@Entity   // Notação que Vai dizer que essa Sacola vai virar uma tabela no banco.
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler"}) // Devido à fetch LAZY que as vezes dá erro no console quando o JSON e o Hibernate estão rodando juntos.
@NoArgsConstructor // COnstrutor criado sem argumentos (O Hibernate pede isso)- (Ver no Structure)


public class Sacola {
    @Id                                             // Toda tabela tem um Identificador único de cada elemento no banco de dados relacional, é a primary key. A gente é obrigado a ter primary key. Essa notação vai dizer para a aplicação que a primary key existe e onde esdtá.
    @GeneratedValue(strategy = GenerationType.AUTO) // Esse parenteses mostra como os identificadores unicos serão gerados e salvos no banco de dados. 
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // ManyToOne diz que 1 cliente pode ter VÁRIAS sacolas.
    @JsonIgnore
    private Cliente cliente;                        // Como estou relacionando tabelas, preciso utilizar a notação de relacionamento de entidades.
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;
    @Enumerated
    private FormaPagamento formaPagamento;
    private boolean fechada;

}
