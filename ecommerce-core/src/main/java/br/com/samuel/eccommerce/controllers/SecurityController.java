package br.com.samuel.eccommerce.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.eccommerce.security.models.UsuarioAutenticado;

@RestController
public class SecurityController {
    
    @GetMapping("user/auth")
    public UsuarioAutenticado obterUsuarioLogado() {
        return (UsuarioAutenticado) SecurityContextHolder
                                    .getContext()
                                    .getAuthentication()
                                    .getPrincipal();
        
    }
}
