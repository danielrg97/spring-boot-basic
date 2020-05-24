package co.com.daniel.dao;

import co.com.daniel.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
