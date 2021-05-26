package br.com.samuel.ecommerce.controllers;

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

import br.com.samuel.ecommerce.models.Imagem;
import br.com.samuel.ecommerce.services.ServicoUpload;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class ControladorUpload {
   
    @Autowired
    private ServicoUpload servicoUpload;

    @PostMapping("/produtos")
    public ResponseEntity<Imagem> carregarImagemDoProduto(@RequestParam MultipartFile arquivo) throws IOException {
        return servicoUpload.salvarImagemDoProduto(arquivo);
    }

    @GetMapping("produtos/{nomeArquivo}")
    public ResponseEntity<Object> baixarImagemDoProduto(@PathVariable String nomeArquivo) throws IOException{
        return servicoUpload.baixarImagemDoProduto(nomeArquivo);
    }

    @DeleteMapping("/{nomeArquivo}")
    public ResponseEntity<?> removerProduto(@PathVariable String nomeArquivo) throws IOException {
        return servicoUpload.apagarImagemProduto(nomeArquivo);
    }
}