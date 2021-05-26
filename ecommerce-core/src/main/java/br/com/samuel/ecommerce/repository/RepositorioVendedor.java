package br.com.samuel.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.samuel.ecommerce.models.Vendedor;

@Repository
public interface RepositorioVendedor extends JpaRepository<Vendedor, Integer> {
    
    @Query("SELECT v FROM Vendedor v WHERE LOWER(v.nomeFantasia) LIKE %:filtro% ORDER BY v.nomeFantasia ASC")
	Page<Vendedor> listarVendedoresFiltrandoPeloNomeFantasia(@Param("filtro") String filtro, Pageable pageable);
}