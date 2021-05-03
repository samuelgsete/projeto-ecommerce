package br.com.samuel.eccommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.samuel.eccommerce.models.Pedido;

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE LOWER(p.cliente.nome) LIKE %:filtro% ORDER BY p.cliente.nome ASC")
	Page<Pedido> buscarPedidoFiltrandoPeloNome(@Param("filtro") String filtro, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId ORDER BY p.feitoEm DESC")
    Page<Pedido> buscarPedidosPorIdCliente(@Param("clienteId") Integer clienteId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.negocioId = :negocioId")
    Page<Pedido> buscarPedidosPorIdNegocio(@Param("negocioId") Integer negocioId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.negocioId = :negocioId ORDER BY p.feitoEm DESC")
    Page<Pedido> buscarPedidosMaisRecentes(@Param("negocioId") Integer negocioId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.negocioId = :negocioId ORDER BY p.feitoEm ASC")
    Page<Pedido> buscarPedidosMaisAntigos(@Param("negocioId") Integer negocioId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.negocioId = :negocioId AND p.situacao = 'PEDIDO_CANCELADO'")
    Page<Pedido> buscarPedidosCancelados(@Param("negocioId") Integer negocioId, Pageable pageable);
}