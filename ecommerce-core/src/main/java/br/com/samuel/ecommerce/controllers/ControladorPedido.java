package br.com.samuel.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.ecommerce.exceptions.models.EstoqueVazioException;
import br.com.samuel.ecommerce.models.Pedido;
import br.com.samuel.ecommerce.models.enuns.SituacaoPedido;
import br.com.samuel.ecommerce.security.models.Perfis;
import br.com.samuel.ecommerce.services.ServicoPedido;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class ControladorPedido {

    @Autowired
    private ServicoPedido servicoPedido;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) {
        return servicoPedido.buscarPedidoPorId(id);
    }

    @Secured({ Perfis.ROLE_ADMIN })
    @GetMapping
    public Page<Pedido> listarPedidosPorIdNegocio(@RequestParam Integer negocioId, @RequestParam String filtro, Pageable pageable) { 
        return servicoPedido.listarPedidosPorIdNegocio(negocioId, filtro, pageable);
    }

    @Secured({ Perfis.ROLE_CLIENT })
    @GetMapping("/cliente/{clienteId}")
    public Page<Pedido> listarPedidosPorIdCliente(@PathVariable Integer clienteId, Pageable pageable) { 
        return servicoPedido.listarPedidosPorIdCliente(clienteId, pageable);
    }

    @Secured({ Perfis.ROLE_CLIENT })
    @PostMapping
    public ResponseEntity<Pedido> fazerPedido(@RequestBody @Valid Pedido pedido) throws EstoqueVazioException {
        return servicoPedido.fazerPedido(pedido);
    }

    @Secured({ Perfis.ROLE_ADMIN })
    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> atualizarSituacaoPedido(@PathVariable Integer id, @RequestParam SituacaoPedido situacao) {
        return servicoPedido.atualizarSituacaoPedido(id, situacao);
    }

    @Secured({ Perfis.ROLE_CLIENT })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable Integer id) {
        return servicoPedido.cancelarPedido(id);
    }
}