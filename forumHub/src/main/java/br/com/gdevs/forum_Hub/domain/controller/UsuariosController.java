package br.com.gdevs.forum_Hub.domain.controller;

import br.com.gdevs.forum_Hub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    GerenciadorDeUsuarios userGen;

    @GetMapping
    public ResponseEntity listarUsuarios(){
        List<DadosDetalhamentoUsuario> usuarios = repository.findAllByAtivoTrue()
                .stream().map(DadosDetalhamentoUsuario::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id, @RequestBody @Valid DadosExclusaoUsuario dados){
        userGen.excluir(id, dados);
        return ResponseEntity.noContent().build();
    }


}
