package br.com.samuel.eccommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import br.com.samuel.eccommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.eccommerce.models.Negocio;
import br.com.samuel.eccommerce.repository.RepositorioNegocio;

@Service
public class ServicoNegocio {
    
    @Autowired
    private RepositorioNegocio repositorioNegocio;

    public ResponseEntity<Negocio> buscarNegocioPorId(Integer id) {
        return repositorioNegocio
                    .findById(id)
                    .map(negocio -> ResponseEntity.ok().body(negocio))
                    .orElse(ResponseEntity.notFound().build());
    }

    public Page<Negocio> listarNegociosFiltrandoPeloNomeFantasia(String filtro, Pageable pageable) { 
        return repositorioNegocio.listarNegociosFiltrandoPeloNomeFantasia(filtro, pageable);
    }

    public ResponseEntity<Object> criarNegocio(Negocio negocio) throws EmailCadastradoException {
        var NegocioCadastrado = repositorioNegocio.buscarPorEmail(negocio.getEmail());
        if(NegocioCadastrado.isPresent())
            throw new EmailCadastradoException("O Email já está sendo utilizado"); 
                  
        var negocioCriado = repositorioNegocio.save(negocio);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(negocioCriado.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Negocio> atualizarNegocio(Integer id, Negocio negocio) {
        return repositorioNegocio
                .findById(id)
                .map(negocioDesatualizado -> {
                    negocioDesatualizado.setNomeFantasia(negocio.getNomeFantasia());
                    negocioDesatualizado.setProprietario(negocio.getProprietario());
                    negocioDesatualizado.setTelefone(negocio.getTelefone());
                    negocioDesatualizado.setEmail(negocio.getEmail());
                    negocioDesatualizado.setSenha(negocio.getSenha());
                    negocioDesatualizado.setDescricao(negocio.getDescricao());
                    negocioDesatualizado.setPedidosConcluidos(negocio.getPedidosConcluidos());
                    negocioDesatualizado.setPedidosCancelados(negocio.getPedidosCancelados());
                    negocioDesatualizado.setTotalProdutosVendidos(negocio.getTotalProdutosVendidos());
                    var negocioAtualizado = repositorioNegocio.save(negocioDesatualizado);
                    return ResponseEntity.ok().body(negocioAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> removerNegocio(Integer id) {
        return repositorioNegocio
                .findById(id)
                .map(negocioRemovido -> {
                    repositorioNegocio.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public void incrementarContadorDeClientes(Integer negocioId) {
        repositorioNegocio
            .findById(negocioId)
            .map(negocioDesatualizado -> {
                var totalClientesAtualizado = negocioDesatualizado.getTotalClientes() + 1;
                negocioDesatualizado.setTotalClientes(totalClientesAtualizado);
                return repositorioNegocio.save(negocioDesatualizado);
            });
    }

    public void decrementarContadorDeClientes(Integer negocioId) {
        repositorioNegocio
            .findById(negocioId)
            .map(negocioDesatualizado -> {
                var totalClientesAtualizado = negocioDesatualizado.getTotalClientes() - 1;
                negocioDesatualizado.setTotalClientes(totalClientesAtualizado);
                return repositorioNegocio.save(negocioDesatualizado);
            });
    }
}