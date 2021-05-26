package br.com.samuel.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.samuel.ecommerce.models.Cliente;
import br.com.samuel.ecommerce.models.ItemPedido;
import br.com.samuel.ecommerce.repository.RepositorioCliente;
import br.com.samuel.ecommerce.repository.RepositorioProduto;
import br.com.samuel.ecommerce.repository.RepositorioVendedor;

import java.util.Set;

@Service
public class ServicoVenda {
    
    @Autowired
    private RepositorioProduto repositorioProduto;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioVendedor repositorioVendedor;

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
            repositorioProduto.save(produto);
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
        repositorioCliente.save(cliente);
    }

    public void processarVendedor(Integer vendedorId, double custoPedido, Set<ItemPedido> itens) {
        var contadorDeProdutos = 0;
        for (var itemPedido: itens)
            contadorDeProdutos += itemPedido.getQuantidade();

        var vendedor = repositorioVendedor.findById(vendedorId).get();
        var receita = vendedor.getReceita() + custoPedido;
        var pedidosConcluidos = vendedor.getPedidosConcluidos() + 1;
        var totalProdutos = vendedor.getTotalProdutosVendidos();

        vendedor.setReceita(receita);
        vendedor.setPedidosConcluidos(pedidosConcluidos);
        vendedor.setTotalProdutosVendidos(contadorDeProdutos + totalProdutos);
        repositorioVendedor.save(vendedor);
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
            repositorioProduto.save(produto);
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
        repositorioCliente.save(cliente);
    }

    public void cancelarProcessamentoVendedor(Integer vendedorId, double custo, Set<ItemPedido> itens) {
        var contadorDeProdutos = 0;
        for (var itemPedido: itens)
            contadorDeProdutos += itemPedido.getQuantidade();

        var vendedor = repositorioVendedor.findById(vendedorId).get();
        var receita = vendedor.getReceita() - custo;
        var totalProdutos = vendedor.getTotalProdutosVendidos();

        vendedor.setReceita(receita);
        vendedor.setTotalProdutosVendidos(contadorDeProdutos + totalProdutos);
        repositorioVendedor.save(vendedor);
    }
}