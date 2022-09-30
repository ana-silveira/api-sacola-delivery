package me.dio.sacolaapi.service.impl;
import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.enumeration.FormaPagamento;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Restaurante;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.repository.ItemRepository;
import me.dio.sacolaapi.repository.ProdutoRepository;
import me.dio.sacolaapi.repository.SacolaRepository;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Essa classe vai implementar todos os métodos que eu defini na Interface SacolaService


@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if (sacola.isFechada()){
            throw new RuntimeException("Esta sacola está fechada.");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(// Método de resposta para quando o id passado para o banco não existir.
                        () -> {
                            throw new RuntimeException("Esse produto não existe!");
                        }
                ))
                .build();

        /* Só é possivel adicionar na sacola ítens do mesmo restaurante. Para adicionar itens de outro restaurante,
        será necessário fechar a sacola do 1o restaurante, concluir pagamento e esperar chegar, ou esvaziá-la,
        para só então adicionar itens do novo restaurante.
        Aqui será feita essa validação:
        */


        List<Item> itensDaSacola = sacola.getItens();
        if(itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante(); //Aqui descubro qual é o restaurante do 1o produto da lista
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)){
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException(
                        "Não é possivel adicionar produtos de restaurantes diferentes. " +
                                "Feche a sacola e conclua a sua compra, ou esvazie-a.");
            }
        }

        List<Double> valorDosItens = new ArrayList<>(); // lista para salvar os valores do produtos com suas quantidades
        for(Item itemDaSacola:itensDaSacola) {
            double valorTotalDoItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalDoItem);
        }

        Double valorTotalSacola = 0.0;
        for(Double valorDeCadaItem : valorDosItens) {
            valorTotalSacola += valorDeCadaItem;
        }

        /* O for acima também poderia ser escrito desta forma:
        double valorTotalSacola = valorDosItens.stream()
        .mapToDouble(valorTotalDeCadaItem --> valorTotalDeCadaItem)
        .sum();
         */

        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);

        return itemRepository.save(itemParaSerInserido);
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow( // Método de resposta para quando o id passado para o banco não existir.
                () -> {
                    throw new RuntimeException("Essa sacola não existe!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()){
            throw new RuntimeException("Inclua ítens na sacola!");
        }
        FormaPagamento formaPagamento =
                numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola); // Entrega da sacola atualizada para o BD

    }
}
