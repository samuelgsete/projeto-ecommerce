package br.com.samuel.eccommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import br.com.samuel.eccommerce.models.Negocio;

@Repository
public interface RepositorioNegocio extends JpaRepository<Negocio, Integer> {

    
    @Query("SELECT n FROM Negocio n WHERE LOWER(n.email) = :email")
    Optional<Negocio> buscarPorEmail(@Param("email") String email);
    
    @Query("SELECT n FROM Negocio n WHERE LOWER(n.nomeFantasia) LIKE %:filtro% ORDER BY n.nomeFantasia ASC")
	Page<Negocio> listarNegociosFiltrandoPeloNomeFantasia(@Param("filtro") String filtro, Pageable pageable);
}