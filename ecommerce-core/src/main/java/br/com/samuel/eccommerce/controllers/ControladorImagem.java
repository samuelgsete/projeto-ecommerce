package br.com.samuel.eccommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import br.com.samuel.eccommerce.models.Imagem;
import br.com.samuel.eccommerce.services.ServicoImagem;

@RestController
@RequestMapping("/carregamentos")
public class ControladorImagem {
   
    @Autowired
    private ServicoImagem servicoImagem;

    @PostMapping("/produtos")
    public ResponseEntity<Imagem> carregarImagemDoProduto(@RequestParam MultipartFile arquivo) throws IOException {
        return servicoImagem.salvarImagemDoProduto(arquivo);
    }

    @GetMapping("produtos/{nomeArquivo}")
    public ResponseEntity<Object> baixarImagemDoProduto(@PathVariable String nomeArquivo) throws IOException{
        System.out.println(LocalDateTime.now());
        return servicoImagem.baixarImagemDoProduto(nomeArquivo);
    }

    @DeleteMapping("/{nomeArquivo}")
    public ResponseEntity<?> removerProduto(@PathVariable String nomeArquivo) throws IOException {
        return servicoImagem.apagarImagemProduto(nomeArquivo);
    }
}