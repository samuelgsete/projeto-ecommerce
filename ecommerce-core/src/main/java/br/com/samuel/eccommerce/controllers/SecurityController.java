package br.com.samuel.eccommerce.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.eccommerce.security.models.UsuarioAutenticado;

@RestController
@RequestMapping("/user/auth")
public class SecurityController {
    
    @GetMapping
    public UsuarioAutenticado obterUsuarioLogado() {
        return (UsuarioAutenticado) SecurityContextHolder
                                    .getContext()
                                    .getAuthentication()
                                    .getPrincipal();
        
    }
}
