package br.com.samuel.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.samuel.ecommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.ecommerce.models.Vendedor;
import br.com.samuel.ecommerce.repository.RepositorioUsuario;
import br.com.samuel.ecommerce.repository.RepositorioVendedor;

import java.net.URI;
import java.util.Random;

@Service
public class ServicoVendedor {
    
    @Autowired
    private RepositorioVendedor repositorioVendedor;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Vendedor> buscarVendedorPorId(Integer id) {
        return repositorioVendedor
                    .findById(id)
                    .map(negocio -> ResponseEntity.ok().body(negocio))
                    .orElse(ResponseEntity.notFound().build());
    }

    public Page<Vendedor> listarNegociosFiltrandoPeloNomeFantasia(String filtro, Pageable pageable) { 
        return repositorioVendedor.listarVendedoresFiltrandoPeloNomeFantasia(filtro, pageable);
    }

    public ResponseEntity<Object> criarVendedor(Vendedor vendedor) throws EmailCadastradoException { 
        var novoUsuario = vendedor.getUsuario();
        var usuarioJaExiste = repositorioUsuario.buscarPorEmail(novoUsuario.getEmail());
        if(usuarioJaExiste.isPresent())
            throw new EmailCadastradoException("O Email já está sendo utilizado"); 

        var codigo = gerarCodigoAleatorio();
        novoUsuario.setCodigoVerificacao(codigo); 

        var senha = novoUsuario.getSenha();
        novoUsuario.setSenha(passwordEncoder.encode(senha));
        vendedor.setUsuario(novoUsuario);

        var vendedorCriado = repositorioVendedor.save(vendedor);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(vendedorCriado.getUsuario().getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Vendedor> atualizarVendedor(Integer id, Vendedor negocio) {
        return repositorioVendedor
                .findById(id)
                .map(vendedorDesatualizado -> {
                    vendedorDesatualizado.setUsuario(negocio.getUsuario());
                    vendedorDesatualizado.setNomeFantasia(negocio.getNomeFantasia());
                    vendedorDesatualizado.setDescricao(negocio.getDescricao());
                    var vendedorAtualizado = repositorioVendedor.save(vendedorDesatualizado);
                    return ResponseEntity.ok().body(vendedorAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> removerVendedor(Integer id) {
        return repositorioVendedor
                .findById(id)
                .map(vendedorRemovido -> {
                    repositorioVendedor.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public void incrementarContadorDeClientes(Integer vendedorId) {
        repositorioVendedor
            .findById(vendedorId)
            .map(vendedorDesatualizado -> {
                var totalClientesAtualizado = vendedorDesatualizado.getTotalClientes() + 1;
                vendedorDesatualizado.setTotalClientes(totalClientesAtualizado);
                return repositorioVendedor.save(vendedorDesatualizado );
            });
    }

    public void decrementarContadorDeClientes(Integer vendedorId) {
        repositorioVendedor
            .findById(vendedorId)
            .map(vendedorDesatualizado -> {
                var totalClientesAtualizado = vendedorDesatualizado.getTotalClientes() - 1;
                vendedorDesatualizado.setTotalClientes(totalClientesAtualizado);
                return repositorioVendedor.save(vendedorDesatualizado);
            });
    }

    private String gerarCodigoAleatorio() {
        var max = 999999;
        var min = 100000;
        Random random = new Random();
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }
}