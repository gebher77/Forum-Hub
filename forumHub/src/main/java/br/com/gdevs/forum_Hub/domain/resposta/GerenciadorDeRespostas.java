package br.com.gdevs.forum_Hub.domain.resposta;

import br.com.gdevs.forum_Hub.domain.topico.TopicoRepository;
import br.com.gdevs.forum_Hub.domain.usuario.UsuarioRepository;
import br.com.gdevs.forum_Hub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadorDeRespostas {

    @Autowired
    RespostaRepository repository;

    @Autowired
    UsuarioRepository userRepo;

    @Autowired
    TopicoRepository topicoRepo;



    public DadosDetalhamentoResposta criarResposta (DadosCriacaoResposta dados){

        if(!topicoRepo.existsById(dados.topico_id())){
            throw new ValidacaoException("O tópico informado não existe!");
        }
        if(!userRepo.existsById(dados.usuario_id())){
            throw new ValidacaoException("O usuário informado não existe!");
        }

        var usuario = userRepo.getReferenceById(dados.usuario_id());
        var topico = topicoRepo.getReferenceById(dados.topico_id());

        var resposta = new Resposta(topico, usuario, dados);

        repository.save(resposta);
        return new DadosDetalhamentoResposta(resposta);
    }

    public String excluir (DadosExclusaoResposta dados, Long id){
        Optional<Resposta> resposta = repository.findById(id);
        if(resposta.isEmpty()){
            throw new ValidacaoException("Esta resposta não pôde ser deletada, pois não existe!");
        }
        if(!resposta.get().getUsuario().getEmail().equals(dados.email()) && !resposta.get().getTopico().getUsuario().getEmail().equals(dados.senha())){
            throw new ValidacaoException("Você só pode excluir respostas de sua autoria, a menos que seja o criador do tópico.");
        }

        repository.delete(resposta.get());

        return "Resposta: " + resposta.get() + " deletada com sucesso."  ;



    }


}
