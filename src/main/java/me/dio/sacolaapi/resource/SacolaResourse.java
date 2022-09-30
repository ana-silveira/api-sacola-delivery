package me.dio.sacolaapi.resource;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.SacolaService;
import org.springframework.web.bind.annotation.*;


@Api(value="ifood-devweek/sacolas")
@RestController                                       // É nesta classe que estarão os meus endpoints. O spring vai saber disso com a anotação!
@RequestMapping("ifood-devweek/sacolas")              //Aqui defino como os meus endpoints vão ser escritos.
@RequiredArgsConstructor
public class SacolaResourse {

    private final SacolaService sacolaService;

    @PostMapping                                     // Método de requisição HTTP para item na sacola
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){     // De onde vem o itemDto? É do corpo da requisição. -> Usa o @RequestBody
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping ("/{id}")                           //Pq eu quero que a minha aplicação vá até o servidor e retorne uma informação que está no (BD).
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")       // Só quero alterar 1 ou 2 atributos, por isso eu escolhi o Patch e não o Put. O Put é para muitas informações a serem alteradas.
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }

}
