package br.com.samuel.eccommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.samuel.eccommerce.models.Perfil;
import br.com.samuel.eccommerce.repository.RepositorioPerfil;
import br.com.samuel.eccommerce.security.models.Constantes;

import java.util.Arrays;
import java.util.List;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RepositorioPerfil repositorioPerfil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        List<Perfil> perfis = repositorioPerfil.findAll();

        if(perfis.isEmpty()) {
            Perfil perfilCliente = new Perfil(Constantes.ROLE_CLIENT);
            Perfil perfilGerente = new Perfil(Constantes.ROLE_ADMIN);
            
            perfis = Arrays.asList(perfilCliente, perfilGerente);

            repositorioPerfil.saveAll(perfis);
        }
    }
}