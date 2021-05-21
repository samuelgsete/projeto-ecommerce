package br.com.samuel.eccommerce.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import br.com.samuel.eccommerce.models.Endereco;

@Repository
public interface RepositorioEndereco extends JpaRepository<Endereco, Integer> {
    
    @Query("SELECT e FROM Endereco e WHERE e.usuario.id = :usuarioId")
    Optional<Endereco> buscarEnderecoDoUsuario(@Param("usuarioId") Integer usuarioId);
}