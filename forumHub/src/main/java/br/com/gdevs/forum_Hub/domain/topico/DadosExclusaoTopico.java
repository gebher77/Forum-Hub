package br.com.gdevs.forum_Hub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosExclusaoTopico ( @NotBlank @Email String email, @NotBlank String senha) {
}
