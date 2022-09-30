package me.dio.sacolaapi.service;

import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.resource.dto.ItemDto;

public interface SacolaService {
    //Endpoints: 1) VER sacola, 2)INCLUIR ítens na sacola e 3)FECHAR sacola.
    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento); // existe um número definido para cada opção no enum FormaPagamento
}
