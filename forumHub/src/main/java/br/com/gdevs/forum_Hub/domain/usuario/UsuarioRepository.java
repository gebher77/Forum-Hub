package br.com.gdevs.forum_Hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String subject);

    List<Usuario> findAllByAtivoTrue();
}
