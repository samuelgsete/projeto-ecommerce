package br.com.samuel.eccommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.samuel.eccommerce.exceptions.models.EstoqueVazioException;
import br.com.samuel.eccommerce.models.Pedido;
import br.com.samuel.eccommerce.models.enuns.SituacaoPedido;
import br.com.samuel.eccommerce.repository.RepositorioPedido;

@Service
public class ServicoPedido {
    
    @Autowired
    private RepositorioPedido repositorioPedido;
    @Autowired
    private ServicoVenda servicoVenda;

    public ResponseEntity<Pedido> buscarPedidoPorId(Integer id) {
        return repositorioPedido
                    .findById(id)
                    .map(pedido -> ResponseEntity.ok().body(pedido))
                    .orElse(ResponseEntity.notFound().build());
    }

    public Page<Pedido> listarPedidosPorIdCliente(Integer clienteId, Pageable pageable) { 
        return repositorioPedido.buscarPedidosPorIdCliente(clienteId, pageable);
    }

    public Page<Pedido> listarPedidosPorIdNegocio(Integer negocioId, String filtro, Pageable pageable) { 
        if(filtro.toLowerCase().equals("recentes"))
            return repositorioPedido.buscarPedidosMaisRecentes(negocioId, pageable);

        else if (filtro.toLowerCase().equals("antigos"))
            return repositorioPedido.buscarPedidosMaisAntigos(negocioId, pageable);

        else if(filtro.toLowerCase().equals("cancelados"))
            return repositorioPedido.buscarPedidosCancelados(negocioId, pageable);

        return repositorioPedido.buscarPedidosPorIdNegocio(negocioId, pageable);
    }

    public ResponseEntity<Pedido> fazerPedido(Pedido pedido) throws EstoqueVazioException {
        var itens = pedido.getItens();
        for (var itemPedido : itens) {
            var produto = itemPedido.getProduto();
            var quantidade = itemPedido.getQuantidade();
            if(quantidade > produto.getEstoque())
                throw new EstoqueVazioException("O produto ".concat(produto.getNome()).concat(" está em falta"));
        } 

        System.out.println(pedido.getFeitoEm().toString());
        var pedidoSalvo = repositorioPedido.save(pedido);   
        for (var itemPedido : itens)
            itemPedido.setPedido(pedidoSalvo);

        pedidoSalvo = repositorioPedido.save(pedido); 
        servicoVenda.processarProdutos(itens);
        servicoVenda.processarCliente(pedido.getCliente(), pedido.getCusto(), pedido.getItens());
        servicoVenda.processarNegocio(pedido.getAdminId(), pedido.getCusto(), pedido.getItens());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }

    public ResponseEntity<Pedido> atualizarSituacaoPedido(Integer id, SituacaoPedido situacao) {
        return repositorioPedido
                .findById(id)
                .map(pedidoDesatualizado -> {
                    pedidoDesatualizado.setSituacao(situacao);
                    var pedidoAtualizado = repositorioPedido.save(pedidoDesatualizado);
                    return ResponseEntity.ok().body(pedidoAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> cancelarPedido(Integer id) {
        return repositorioPedido
                .findById(id)
                .map(pedidoCancelado -> {
                    pedidoCancelado.setSituacao(SituacaoPedido.PEDIDO_CANCELADO);
                    repositorioPedido.save(pedidoCancelado);
                    servicoVenda.cancelarProcessamentoProdutos(pedidoCancelado.getItens());
                    servicoVenda.cancelarProcessamentoCliente(pedidoCancelado.getCliente(), pedidoCancelado.getCusto(), pedidoCancelado.getItens());
                    servicoVenda.cancelarProcessamentoNegocio(pedidoCancelado.getAdminId(), pedidoCancelado.getCusto(), pedidoCancelado.getItens());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}