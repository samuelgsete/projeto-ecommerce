package br.com.samuel.eccommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import br.com.samuel.eccommerce.models.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = :email")
    Optional<Usuario> buscarPorEmail(@Param("email") String email);
}