package br.com.samuel.eccommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import br.com.samuel.eccommerce.models.Perfil;

public interface RepositorioPerfil extends JpaRepository<Perfil, Integer> {
    
    @Query("SELECT p FROM Perfil p WHERE LOWER(p.nome) = :nome")
    Optional<Perfil> buscarPorNome(@Param("nome") String nome);
} 