package br.com.samuel.eccommerce.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import br.com.samuel.eccommerce.models.Usuario;

public class UsuarioAutenticado implements UserDetails {
    
    private Usuario usuario;

    public UsuarioAutenticado(Usuario usuario) {
        super();
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getPerfis();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.getEstaAtivo();
    }

    public Integer getId() {
        return usuario.getId();
    }

    public String getNome() {
        return usuario.getNome();
    }
}