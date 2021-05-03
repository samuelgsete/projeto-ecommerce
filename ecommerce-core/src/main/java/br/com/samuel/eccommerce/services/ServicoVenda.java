package br.com.samuel.eccommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import br.com.samuel.eccommerce.models.Cliente;
import br.com.samuel.eccommerce.models.ItemPedido;

@Service
public class ServicoVenda {
    
    @Autowired
    private ServicoProduto servicoProduto;
    @Autowired
    private ServicoCliente servicoCliente;
    @Autowired
    private ServicoNegocio servicoNegocio;

    public void processarProdutos(Set<ItemPedido> itens) {
        for (var itemPedido: itens) {
            var produto = itemPedido.getProduto();
            var quantidade = itemPedido.getQuantidade();
            var unidadesVendidas = produto.getUnidadesVendidas();
            var estoque = produto.getEstoque();
            unidadesVendidas += quantidade;
            estoque -= quantidade;
            produto.setUnidadesVendidas(unidadesVendidas);
            produto.setEstoque(estoque);
            servicoProduto.atualizarProduto(produto.getId(), produto);
        }
    }

    public void processarCliente(Cliente cliente, double custo, Set<ItemPedido> itens) {
        var contadorDeProdutos = 0;
        for (var itemPedido: itens)
            contadorDeProdutos += itemPedido.getQuantidade();

        var totalPedidos = cliente.getTotalPedidos() + 1;
        var totalGasto = cliente.getTotalGasto() + custo;
        var totalConsumido = cliente.getTotalConsumido() + contadorDeProdutos;
        cliente.setTotalPedidos(totalPedidos);
        cliente.setTotalGasto(totalGasto);
        cliente.setTotalConsumido(totalConsumido);
        servicoCliente.atualizarCliente(cliente.getId(), cliente);
    }

    public void processarNegocio(Integer negocioId, double custoPedido, Set<ItemPedido> itens) {
        var contadorDeProdutos = 0;
        for (var itemPedido: itens)
            contadorDeProdutos += itemPedido.getQuantidade();

        var negocio = servicoNegocio.buscarNegocioPorId(negocioId).getBody();
        var receita = negocio.getReceita() + custoPedido;
        var pedidosConcluidos = negocio.getPedidosConcluidos() + 1;
        var totalProdutos = negocio.getTotalProdutosVendidos();
        negocio.setReceita(receita);
        negocio.setPedidosConcluidos(pedidosConcluidos);
        negocio.setTotalProdutosVendidos(contadorDeProdutos + totalProdutos);
        servicoNegocio.atualizarNegocio(negocio.getId(), negocio);
    }

    public void cancelarProcessamentoProdutos(Set<ItemPedido> itens) {
        for (var itemPedido: itens) {
            var produto = itemPedido.getProduto();
            var quantidade = itemPedido.getQuantidade();
            var unidadesVendidas = produto.getUnidadesVendidas();
            var estoque = produto.getEstoque();
            unidadesVendidas -= quantidade;
            estoque += quantidade;
            produto.setUnidadesVendidas(unidadesVendidas);
            produto.setEstoque(estoque);
            servicoProduto.atualizarProduto(produto.getId(), produto);
        }
    }

    public void cancelarProcessamentoCliente(Cliente cliente, double custo, Set<ItemPedido> itens) {
        var contadorDeProdutos = 0;
        for (var itemPedido: itens)
            contadorDeProdutos += itemPedido.getQuantidade();

        var totalGasto = cliente.getTotalGasto() - custo;
        var totalConsumido = cliente.getTotalConsumido() - contadorDeProdutos;
        cliente.setTotalGasto(totalGasto);
        cliente.setTotalConsumido(totalConsumido);
        servicoCliente.atualizarCliente(cliente.getId(), cliente);
    }

    public void cancelarProcessamentoNegocio(Integer negocioId, double custo, Set<ItemPedido> itens) {
        var contadorDeProdutos = 0;
        for (var itemPedido: itens)
            contadorDeProdutos += itemPedido.getQuantidade();
            
        var negocio = servicoNegocio.buscarNegocioPorId(negocioId).getBody();
        var receita = negocio.getReceita() - custo;
        var totalProdutos = negocio.getTotalProdutosVendidos();
        negocio.setReceita(receita);
        negocio.setTotalProdutosVendidos(contadorDeProdutos + totalProdutos);
        servicoNegocio.atualizarNegocio(negocio.getId(), negocio);
    }
}