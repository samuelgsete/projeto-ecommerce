package br.com.samuel.eccommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Random;

import br.com.samuel.eccommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.eccommerce.models.Cliente;
import br.com.samuel.eccommerce.models.enuns.interfaces.HistoricoCliente;
import br.com.samuel.eccommerce.models.enuns.interfaces.ProdutosFavoritos;
import br.com.samuel.eccommerce.repository.RepositorioCliente;
import br.com.samuel.eccommerce.repository.RepositorioUsuario;

@Service
public class ServicoCliente {
    
    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ServicoNegocio servicoNegocio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Cliente> buscarClientePorId(Integer id) {
        return repositorioCliente
                    .buscarClientePorId(id)
                    .map(cliente -> ResponseEntity.ok().body(cliente))
                    .orElse(ResponseEntity.notFound().build());
    }

    public Page<Cliente> listarClientesPorIdNegocio(Integer negocioId, Pageable pageable) {
        return repositorioCliente.listarClientesPorIdNegocio(negocioId, pageable);
    }

    public Page<ProdutosFavoritos> listarProdutosFavoritos(Integer clienteId, Pageable pageable) {
        return repositorioCliente.listarProdutosFavoritos(clienteId, pageable);
    }

    public Page<HistoricoCliente> verHistoricoCompras(Integer clienteId, Pageable pageable) {
        return repositorioCliente.verHistoricoCompras(clienteId, pageable);
    }

    public ResponseEntity<Object> cadastrarCliente(Cliente cliente) throws EmailCadastradoException {     
        var novoUsuario = cliente.getUsuario();
        servicoNegocio.incrementarContadorDeClientes(cliente.getAdminId());
        var usuarioJaExiste = repositorioUsuario.buscarPorEmail(novoUsuario.getEmail());
        
        if(usuarioJaExiste.isPresent())
            throw new EmailCadastradoException("O Email já está sendo utilizado"); 

        var codigo = gerarCodigoAleatorio();
        novoUsuario.setCodigoVerificacao(codigo); 

        var senha = novoUsuario.getSenha();
        novoUsuario.setSenha(passwordEncoder.encode(senha));

        cliente.setUsuario(novoUsuario);
     
        var clienteSalvo = repositorioCliente.save(cliente);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Cliente> atualizarCliente(Integer id, Cliente cliente) {
        return repositorioCliente
                .findById(id)
                .map(clienteDesatualizado -> {
                    clienteDesatualizado.setTotalPedidos(cliente.getTotalPedidos());
                    clienteDesatualizado.setTotalGasto(cliente.getTotalGasto());
                    clienteDesatualizado.setTotalConsumido(cliente.getTotalConsumido());
                    var clienteAtualizado = repositorioCliente.save(clienteDesatualizado);
                    return ResponseEntity.ok().body(clienteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> removerCliente(Integer id) {
        return repositorioCliente
                .findById(id)
                .map(clienteRemovido -> {
                    servicoNegocio.decrementarContadorDeClientes(clienteRemovido.getAdminId());
                    repositorioCliente.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    private String gerarCodigoAleatorio() {
        var max = 999999;
        var min = 100000;
        Random random = new Random();
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }
}