package br.com.samuel.eccommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

import br.com.samuel.eccommerce.exceptions.models.EmailCadastradoException;
import br.com.samuel.eccommerce.models.Endereco;
import br.com.samuel.eccommerce.models.Usuario;
import br.com.samuel.eccommerce.repository.RepositorioEndereco;
import br.com.samuel.eccommerce.repository.RepositorioUsuario;

@Service
public class ServicoUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioEndereco repositorioEndereco;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Usuario> buscarUsuarioPorId(Integer id) {
        return repositorioUsuario
                    .findById(id)
                    .map(usuario -> ResponseEntity.ok().body(usuario))
                    .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Endereco> buscarEnderecoDoUsuario(Integer usuarioId) {
        var buscandoEndereco = repositorioEndereco.buscarEnderecoDoUsuario(usuarioId);
        if(buscandoEndereco.isPresent()) {
            var endereco = buscandoEndereco.get();
            return ResponseEntity.ok().body(endereco);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Usuario> criarUsuario(Usuario novoUsuario) throws EmailCadastradoException {
        var usuarioJaExiste = repositorioUsuario.buscarPorEmail(novoUsuario.getEmail());
        if(usuarioJaExiste.isPresent())
            throw new EmailCadastradoException("O Email já está sendo utilizado"); 

        var codigo = gerarCodigoAleatorio();
        novoUsuario.setCodigoVerificacao(codigo); 

        var senha = novoUsuario.getSenha();
        novoUsuario.setSenha(passwordEncoder.encode(senha));

        var usuarioCriado = repositorioUsuario.save(novoUsuario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    public ResponseEntity<Usuario> atualizarUsuario(Integer id, Usuario usuario) {
        return repositorioUsuario
                    .findById(id)
                    .map(usuarioDesatualizado -> {
                        usuarioDesatualizado.setNome(usuario.getNome());
                        usuarioDesatualizado.setSobrenome(usuario.getSobrenome());
                        usuarioDesatualizado.setTelefone(usuario.getTelefone());
                        var usuarioAtualizado = repositorioUsuario.save(usuarioDesatualizado);
                        return ResponseEntity.ok().body(usuarioAtualizado);
                    }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> atualizarSenha(Integer usuarioId, String novaSenha) {
        return repositorioUsuario
                    .findById(usuarioId)
                    .map(usuario -> {
                        var senhaCriptografada = passwordEncoder.encode(novaSenha);
                        usuario.setSenha(senhaCriptografada);
                        var usuarioAtualizado = repositorioUsuario.save(usuario);
                        return ResponseEntity.ok().body(usuarioAtualizado);
                    }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Endereco> atualizarEnderecoDoUsuario(Integer usuarioId, Endereco endereco) {
        return repositorioEndereco
                    .buscarEnderecoDoUsuario(usuarioId)
                    .map(enderecoDesatualizado ->{
                        enderecoDesatualizado.setRua(endereco.getRua());
                        enderecoDesatualizado.setNumero(endereco.getNumero());
                        enderecoDesatualizado.setCep(endereco.getCep());
                        enderecoDesatualizado.setReferencia(endereco.getReferencia());
                        enderecoDesatualizado.setBairro(endereco.getBairro());
                        enderecoDesatualizado.setMunicipio(endereco.getMunicipio());
                        var enderecoAtualizado = repositorioEndereco.save(enderecoDesatualizado);
                        return ResponseEntity.ok().body(enderecoAtualizado);
                    }).orElse(ResponseEntity.notFound().build());
    }

    private String gerarCodigoAleatorio() {
        var max = 999999;
        var min = 100000;
        Random random = new Random();
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }
}