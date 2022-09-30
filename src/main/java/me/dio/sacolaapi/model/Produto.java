package me.dio.sacolaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor // Construtor com todos os atributos será criado (Ver no Structure)
@Builder // Traz o componente Sacola Builder - AJuda na hora de criar esse objeto.
@Data // Cria todos os Getters e Setters
@Entity   // Notação que Vai dizer que essa classe vai virar uma tabela no banco.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Devido à fetch LAZY que as vezes dá erro no console quando o JSON e o Hibernate estão rodando juntos.
@NoArgsConstructor // COnstrutor criado sem argumentos (O Hibernate pede isso)- (Ver no Structure)


public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private double valorUnitario;
    @Builder.Default                // Sempre que eu criar um produto, vou deixar setado que ele está true . É o Builder Default que está tornando isso padrão. Daria warning se eu comentasse esse @. 
    private Boolean disponivel = true;
    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;

}
