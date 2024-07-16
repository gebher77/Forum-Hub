package br.com.gdevs.forum_Hub.domain.topico;

import br.com.gdevs.forum_Hub.domain.curso.CursoRepository;
import br.com.gdevs.forum_Hub.domain.usuario.UsuarioRepository;
import br.com.gdevs.forum_Hub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenciadorDeTopicos {

    @Autowired
    CursoRepository cursoRepo;
    @Autowired
    UsuarioRepository userRepo;
    @Autowired
    TopicoRepository topicoRepo;

    public DadosDetalhamentoTopico criarTopico(DadosCadastroTopico dados){

        if(!cursoRepo.existsById(dados.curso_id())){
            throw new ValidacaoException("Curso não existe no sistema!");
        }

        if(!userRepo.existsById(dados.usuario_id())){
            throw new ValidacaoException("Usuario não existe!");
        }

        var usuario = userRepo.getReferenceById(dados.usuario_id());
        var curso = cursoRepo.getReferenceById(dados.curso_id());

        var topico = new Topico(usuario, curso, dados);



        topicoRepo.save(topico);
        return new DadosDetalhamentoTopico(topico);
    }

    public String excluir(DadosExclusaoTopico dados, Long id){

        Optional<Topico> topico = topicoRepo.findById(id);

        if(topico.isEmpty()){
            throw new ValidacaoException("Nenhum tópico encontrado com o id informado.");
        }
        if(!(topico.get().getUsuario().getEmail().equals(dados.email())) && !topico.get().getUsuario().getSenha().equals(dados.senha())) {
            throw new ValidacaoException("Não é possível excluir tópicos que não são seus!");
        }else{
            topicoRepo.delete(topico.get());
            return "Tópico "+ topico.get() + " excluído!";
        }

    }




}
