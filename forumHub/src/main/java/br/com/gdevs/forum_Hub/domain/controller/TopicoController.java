package br.com.gdevs.forum_Hub.domain.controller;

import br.com.gdevs.forum_Hub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    GerenciadorDeTopicos topicosGerenciador;
    @Autowired
    TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCadastroTopico dados){

        var topico = topicosGerenciador.criarTopico(dados);

        return ResponseEntity.ok(topico);

    }

    @GetMapping
    public ResponseEntity listarTopicos(){
        var topicos = repository.findAll().stream()
                .map(DadosDetalhamentoTopico::new).collect(Collectors.toList());

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
       return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, @RequestBody  @Valid DadosExclusaoTopico dados){
        topicosGerenciador.excluir(dados, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = repository.getReferenceById(id);
        topico.atualizar(dados);

       return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));


    }

}
