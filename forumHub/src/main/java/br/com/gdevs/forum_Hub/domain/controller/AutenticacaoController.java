package br.com.gdevs.forum_Hub.domain.controller;

import br.com.gdevs.forum_Hub.domain.usuario.DadosLogin;
import br.com.gdevs.forum_Hub.domain.usuario.Usuario;
import br.com.gdevs.forum_Hub.infra.security.DadosTokenJWT;
import br.com.gdevs.forum_Hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados){

        try{
            var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = manager.authenticate(token);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal() );

            return ResponseEntity.ok( new DadosTokenJWT(tokenJWT));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
