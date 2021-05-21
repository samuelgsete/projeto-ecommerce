package br.com.samuel.eccommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.samuel.eccommerce.models.Produto;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE %:filtro% ORDER BY p.nome ASC")
	Page<Produto> findByFilter(@Param("filtro") String filtro, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE p.adminId = :adminId ORDER BY p.nome ASC")
	Page<Produto> listarProdutosPorIdNegocio(@Param("adminId") Integer adminId, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE p.adminId = :adminId ORDER BY p.unidadesVendidas DESC")
    Page<Produto> listarProdutosPopulares(@Param("adminId") Integer adminId, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE p.adminId = :adminId ORDER BY p.preco ASC")
    Page<Produto> listarProdutosMenorPreco(@Param("adminId") Integer adminId, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE p.adminId = :adminId ORDER BY p.preco DESC")
    Page<Produto> listarProdutosMaiorPreco(@Param("adminId") Integer adminId, Pageable pageable);
}