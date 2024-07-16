package br.com.gdevs.forum_Hub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCriacaoResposta(

        @NotBlank
        String mensagem,
        @NotNull
        Long topico_id,
        @NotNull
        Long usuario_id
        ) {
}
