package br.com.samuel.eccommerce.controllers;


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

import javax.validation.Valid;

import br.com.samuel.eccommerce.exceptions.models.EstoqueVazioException;
import br.com.samuel.eccommerce.models.Pedido;
import br.com.samuel.eccommerce.models.enuns.SituacaoPedido;
import br.com.samuel.eccommerce.security.models.Constantes;
import br.com.samuel.eccommerce.services.ServicoPedido;

@RestController
@RequestMapping("/pedidos")
public class ControladorPedido {

    @Autowired
    private ServicoPedido servicoPedido;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) {
        return servicoPedido.buscarPedidoPorId(id);
    }

    @Secured({ Constantes.ROLE_ADMIN })
    @GetMapping
    public Page<Pedido> listarPedidosPorIdNegocio(@RequestParam Integer negocioId, @RequestParam String filtro, Pageable pageable) { 
        return servicoPedido.listarPedidosPorIdNegocio(negocioId, filtro, pageable);
    }

    @Secured({ Constantes.ROLE_CLIENT })
    @GetMapping("/cliente/{clienteId}")
    public Page<Pedido> listarPedidosPorIdCliente(@PathVariable Integer clienteId, Pageable pageable) { 
        return servicoPedido.listarPedidosPorIdCliente(clienteId, pageable);
    }

    @Secured({ Constantes.ROLE_CLIENT })
    @PostMapping
    public ResponseEntity<Pedido> fazerPedido(@RequestBody @Valid Pedido pedido) throws EstoqueVazioException {
        return servicoPedido.fazerPedido(pedido);
    }

    @Secured({ Constantes.ROLE_ADMIN })
    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> atualizarSituacaoPedido(@PathVariable Integer id, @RequestParam SituacaoPedido situacao) {
        return servicoPedido.atualizarSituacaoPedido(id, situacao);
    }

    @Secured({ Constantes.ROLE_CLIENT })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable Integer id) {
        return servicoPedido.cancelarPedido(id);
    }
}