package br.com.samuel.eccommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.samuel.eccommerce.repository.RepositorioUsuario;
import br.com.samuel.eccommerce.security.models.UsuarioAutenticado;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    
    @Autowired
    private RepositorioUsuario repositorio;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
        var usuarioCadastrado =  repositorio.buscarPorEmail(email);

        if(usuarioCadastrado.isEmpty())
            throw new UsernameNotFoundException("Usuário não encontrado");

        var usuario = usuarioCadastrado.get();
        return new UsuarioAutenticado(usuario);
    }
}