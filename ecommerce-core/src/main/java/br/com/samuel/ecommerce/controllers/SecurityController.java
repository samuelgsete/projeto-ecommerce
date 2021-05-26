package br.com.samuel.ecommerce.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.ecommerce.security.models.UsuarioLogado;

@RestController
public class SecurityController {
    
    @GetMapping("user/auth")
    public UsuarioLogado obterUsuarioLogado() {
        return (UsuarioLogado) SecurityContextHolder
                                    .getContext()
                                    .getAuthentication()
                                    .getPrincipal();
        
    }
}
