package br.com.gdevs.forum_Hub.domain.topico;

import br.com.gdevs.forum_Hub.domain.curso.Curso;
import br.com.gdevs.forum_Hub.domain.usuario.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public record DadosCadastroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Status status,
        @NotNull
        Long curso_id,
        @NotNull
        Long usuario_id



) {
}
