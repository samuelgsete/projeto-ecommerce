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
import br.com.samuel.eccommerce.models.Negocio;
import br.com.samuel.eccommerce.services.ServicoNegocio;

@RestController
@RequestMapping("/negocios")
public class ControladorNegocio {
 
    @Autowired
    private ServicoNegocio servicoNegocio;

    @GetMapping("/{id}")
    public ResponseEntity<Negocio> buscarNegocioPorId(@PathVariable Integer id) {
        return servicoNegocio.buscarNegocioPorId(id);
    }

    @GetMapping
    public Page<Negocio> buscarProdutosPaginado(@RequestParam String filtro, Pageable pageable) { 
        return servicoNegocio.listarNegociosFiltrandoPeloNomeFantasia(filtro, pageable); 
    }

    @PostMapping
    public ResponseEntity<Object> criarNegocio(@RequestBody @Valid Negocio negocio) throws EmailCadastradoException {
        return servicoNegocio.criarNegocio(negocio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Negocio> atualizarNegocio(@PathVariable Integer id, @RequestBody @Valid Negocio negocio) {
        return servicoNegocio.atualizarNegocio(id, negocio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerNegocio(@PathVariable Integer id) {
        return servicoNegocio.removerNegocio(id);
    }
}