package br.com.samuel.eccommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import br.com.samuel.eccommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.eccommerce.models.Cliente;
import br.com.samuel.eccommerce.models.enuns.interfaces.HistoricoCliente;
import br.com.samuel.eccommerce.models.enuns.interfaces.ProdutosFavoritos;
import br.com.samuel.eccommerce.services.ServicoCliente;

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
    public Page<Cliente> buscarClientesPorIdNegocio(@RequestParam Integer negocioId, Pageable pageable) { 
        return servicoCliente.listarClientesPorIdNegocio(negocioId, pageable);
    }

    @GetMapping("/{clienteId}/produtos")
    public Page<ProdutosFavoritos> listarProdutosMaisComprados(@PathVariable Integer clienteId, Pageable pageable) {
        return servicoCliente.listarProdutosFavoritos(clienteId, pageable);
    }

    @GetMapping("/{clienteId}/historico")
    public Page<HistoricoCliente> verHistoricoCompraas(@PathVariable Integer clienteId, Pageable pageable) {
        return servicoCliente.verHistoricoCompras(clienteId, pageable);
    }
    
    @PostMapping
    public ResponseEntity<Object> criarCliente(@RequestBody @Valid Cliente cliente) throws EmailCadastradoException {
        return servicoCliente.cadastrarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        return servicoCliente.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Integer id) {
        return servicoCliente.removerCliente(id);
    }
}