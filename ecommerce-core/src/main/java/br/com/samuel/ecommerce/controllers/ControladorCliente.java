package br.com.samuel.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.ecommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.ecommerce.models.Cliente;
import br.com.samuel.ecommerce.models.interfaces.HistoricoCliente;
import br.com.samuel.ecommerce.models.interfaces.ProdutosFavoritos;
import br.com.samuel.ecommerce.services.ServicoCliente;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ControladorCliente {
 
    @Autowired
    private ServicoCliente servicoCliente;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
        return servicoCliente.buscarClientePorId(id);
    }

    @GetMapping
    public Page<Cliente> buscarClientesPorIdUsuarioAdmin(@RequestParam Integer vendedorId, Pageable pageable) { 
        return servicoCliente.listarClientesPorIdVendedor(vendedorId, pageable);
    }

    @GetMapping("/{clienteId}/produtos")
    public Page<ProdutosFavoritos> listarProdutosMaisComprados(@PathVariable Integer clienteId, Pageable pageable) {
        return servicoCliente.listarProdutosFavoritos(clienteId, pageable);
    }

    @GetMapping("/{clienteId}/historico")
    public Page<HistoricoCliente> verHistoricoCompras(@PathVariable Integer clienteId, Pageable pageable) {
        return servicoCliente.verHistoricoCompras(clienteId, pageable);
    }
    
    @PostMapping
    public ResponseEntity<Object> criarCliente(@RequestBody @Valid Cliente cliente) throws EmailCadastradoException {
        return servicoCliente.cadastrarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Integer id) {
        return servicoCliente.removerCliente(id);
    }
}