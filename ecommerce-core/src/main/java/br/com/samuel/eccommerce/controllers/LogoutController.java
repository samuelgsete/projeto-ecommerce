package br.com.samuel.eccommerce.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Autowired
    private DefaultTokenServices tokenServices;

    @PostMapping("user/logout")
    public ResponseEntity<?> fazerLogout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split("Bearer ")[1];
        tokenServices.revokeToken(token);  
        return ResponseEntity.ok().build();
    }
}