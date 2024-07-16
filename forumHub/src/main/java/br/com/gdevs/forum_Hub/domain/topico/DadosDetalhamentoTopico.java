package br.com.gdevs.forum_Hub.domain.topico;

import br.com.gdevs.forum_Hub.domain.curso.Curso;
import br.com.gdevs.forum_Hub.domain.curso.DadosDetalhamentoCurso;
import br.com.gdevs.forum_Hub.domain.resposta.DadosDetalhamentoResposta;
import br.com.gdevs.forum_Hub.domain.resposta.Resposta;
import br.com.gdevs.forum_Hub.domain.usuario.DadosDetalhamentoUsuario;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoTopico (Long id, String titulo, String mensagem, OffsetDateTime data, Status status,
                                       DadosDetalhamentoUsuario autor, DadosDetalhamentoCurso curso, List<DadosDetalhamentoResposta> respostas) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(),new DadosDetalhamentoUsuario(topico.getUsuario()), new DadosDetalhamentoCurso(topico.getCurso()),
                topico.getRespostas().stream()
                        .map(dr -> new DadosDetalhamentoResposta(dr.getId(), dr.getMensagem(), dr.getTopico().getId(),
                                dr.getData(), new DadosDetalhamentoUsuario(dr.getUsuario()) )).collect(Collectors.toList()));
    }

}
