package br.com.gdevs.forum_Hub.domain.controller;

import br.com.gdevs.forum_Hub.domain.curso.Curso;
import br.com.gdevs.forum_Hub.domain.curso.CursoRepository;
import br.com.gdevs.forum_Hub.domain.curso.DadosDetalhamentoCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity listarCursos(){
        List <DadosDetalhamentoCurso> cursos = cursoRepository.findAll().stream()
                .map(curso -> new DadosDetalhamentoCurso(curso))
                .collect(Collectors.toList());
        System.out.println(cursos);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharCurso(@PathVariable Long id){
        var curso = cursoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

}
