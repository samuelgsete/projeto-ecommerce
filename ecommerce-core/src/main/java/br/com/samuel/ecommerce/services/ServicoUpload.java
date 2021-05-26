package br.com.samuel.ecommerce.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.samuel.ecommerce.models.Imagem;

@Service
public class ServicoUpload {
    
    public ResponseEntity<Imagem> salvarImagemDoProduto(MultipartFile arquivo) throws IOException {
        var nomeArquivo = arquivo.getOriginalFilename();
        var caminho = Paths.get("uploads/fotos/produtos/".concat(nomeArquivo));
        Files.copy(arquivo.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
        var urlFoto = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("carregamentos/produtos/")
                            .path(nomeArquivo)
                            .toUriString();
        var imagem = new Imagem(urlFoto);
        return ResponseEntity.ok(imagem);
    }

    public ResponseEntity<Object> baixarImagemDoProduto(String nomeArquivo) throws IOException {
        var caminho = Paths.get("uploads/fotos/produtos/".concat(nomeArquivo));
		var arquivo = new File(caminho.toString());
        var conexaoArquivo = new FileInputStream(arquivo);
        var img = new  ByteArrayResource(conexaoArquivo.readAllBytes());
        conexaoArquivo.close();
		return ResponseEntity
                .ok()
				.contentLength(arquivo.length())
				.contentType(MediaType.IMAGE_JPEG)
                .body(img);
	}

    public ResponseEntity<Object> apagarImagemProduto(String nomeArquivo) throws IOException {
        var caminho = Paths.get("uploads/fotos/produtos/".concat(nomeArquivo));
		var arquivo = new File(caminho.toString());
        arquivo.delete();
        return ResponseEntity.ok().build();
    }
}