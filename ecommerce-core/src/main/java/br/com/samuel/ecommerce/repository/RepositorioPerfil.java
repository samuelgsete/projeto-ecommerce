package br.com.samuel.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.samuel.ecommerce.models.Perfil;

import java.util.Optional;

public interface RepositorioPerfil extends JpaRepository<Perfil, Integer> {
    
    @Query("SELECT p FROM Perfil p WHERE LOWER(p.nome) = :nome")
    Optional<Perfil> buscarPorNome(@Param("nome") String nome);
} 