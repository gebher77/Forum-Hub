package br.com.gdevs.forum_Hub.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String email) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

}
