package br.com.gdevs.forum_Hub.domain.controller;

import br.com.gdevs.forum_Hub.domain.resposta.DadosCriacaoResposta;
import br.com.gdevs.forum_Hub.domain.resposta.DadosExclusaoResposta;
import br.com.gdevs.forum_Hub.domain.resposta.GerenciadorDeRespostas;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    GerenciadorDeRespostas respostaGen;



    @PostMapping
    @Transactional
    public ResponseEntity criarResposta (@RequestBody @Valid DadosCriacaoResposta dados){

        var resposta = respostaGen.criarResposta(dados);

        return  ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirResposta(@PathVariable Long id, @RequestBody @Valid DadosExclusaoResposta dados){
        respostaGen.excluir(dados, id);

        return ResponseEntity.noContent().build();
    }

}
