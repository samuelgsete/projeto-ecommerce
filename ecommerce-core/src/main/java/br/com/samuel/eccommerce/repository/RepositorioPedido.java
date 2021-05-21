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

    @Query("SELECT p FROM Pedido p WHERE LOWER(p.cliente.usuario.nome) LIKE %:filtro% ORDER BY p.cliente.usuario.nome ASC")
	Page<Pedido> buscarPedidoFiltrandoPeloNome(@Param("filtro") String filtro, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.usuario.id = :clienteId ORDER BY p.feitoEm DESC")
    Page<Pedido> buscarPedidosPorIdCliente(@Param("clienteId") Integer clienteId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.adminId = :adminId")
    Page<Pedido> buscarPedidosPorIdNegocio(@Param("adminId") Integer adminId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.adminId = :adminId ORDER BY p.feitoEm DESC")
    Page<Pedido> buscarPedidosMaisRecentes(@Param("adminId") Integer adminId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.adminId = :adminId ORDER BY p.feitoEm ASC")
    Page<Pedido> buscarPedidosMaisAntigos(@Param("adminId") Integer adminId, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.adminId = :adminId AND p.situacao = 'PEDIDO_CANCELADO'")
    Page<Pedido> buscarPedidosCancelados(@Param("adminId") Integer adminId, Pageable pageable);
}