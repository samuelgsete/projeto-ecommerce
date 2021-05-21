package br.com.samuel.eccommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.eccommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.eccommerce.models.Endereco;
import br.com.samuel.eccommerce.models.Usuario;
import br.com.samuel.eccommerce.services.ServicoUsuario;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    
    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping("{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        return servicoUsuario.buscarUsuarioPorId(id);
    }

    @GetMapping("{id}/endereco")
    public ResponseEntity<Endereco> buscarEnderecoDoUsuario(@PathVariable Integer id) {
        return servicoUsuario.buscarEnderecoDoUsuario(id);
    }
    
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario novoUsuario) throws EmailCadastradoException {
        return servicoUsuario.criarUsuario(novoUsuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody @Valid Usuario usuario) {
        return servicoUsuario.atualizarUsuario(id, usuario);
    }

    @PutMapping("{id}/endereco")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody @Valid Endereco endereco) {
        return servicoUsuario.atualizarEnderecoDoUsuario(id, endereco);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> atualizarSenha(@PathVariable Integer id, @RequestBody String novaSenha) {
        return servicoUsuario.atualizarSenha(id, novaSenha);
    }
}