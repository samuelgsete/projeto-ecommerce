package br.com.samuel.eccommerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.samuel.eccommerce.models.Cliente;
import br.com.samuel.eccommerce.models.enuns.interfaces.HistoricoCliente;
import br.com.samuel.eccommerce.models.enuns.interfaces.ProdutosFavoritos;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.email) = :email")
    Optional<Cliente> buscarPorEmail(@Param("email") String email);

    @Query("SELECT c FROM Cliente c WHERE c.negocioId = :negocioId ORDER BY c.totalGasto ASC")
	Page<Cliente> listarClientesPorIdNegocio(@Param("negocioId") Integer negocioId, Pageable pageable);

    @Query("SELECT SUM(itempedido.quantidade) AS quantidade, produto AS produto " +
            "FROM Cliente cliente " +
            "JOIN Pedido pedido ON cliente.id = pedido.cliente.id " +
            "JOIN ItemPedido itempedido ON pedido.id = itempedido.pedido.id " +
            "JOIN Produto produto ON produto.id = itempedido.produto.id " +
            "WHERE cliente.id = :clienteId " +
            "GROUP BY produto " +
            "ORDER BY quantidade DESC")
    Page<ProdutosFavoritos> listarProdutosFavoritos(@Param("clienteId") Integer clienteId, Pageable pageable);

    @Query("SELECT cliente.nome AS nomeCliente, pedido.feitoEm AS dataCompra, itempedido.quantidade AS quantidade, produto.preco AS preco, produto.urlImagem AS urlImagem, produto.nome AS nomeProduto " +
            "FROM Cliente cliente " +
            "JOIN Pedido pedido ON cliente.id = pedido.cliente.id " +
            "JOIN ItemPedido itempedido ON pedido.id = itempedido.pedido.id " +
            "JOIN Produto produto ON produto.id = itempedido.produto.id " +
            "WHERE cliente.id = :clienteId " +
            "ORDER BY quantidade DESC")
    Page<HistoricoCliente> verHistoricoCompras(@Param("clienteId") Integer clienteId, Pageable pageable);
}