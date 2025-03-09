package com.fiap.myapp.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/files")
public class FileUploadController {
    
    // Diretório para salvar os arquivos enviados
    private static final String UPLOAD_DIR = "uploads/";

    // Rota para exibir o formulário de upload
    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";  // Retorna a view "upload.html"
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Verifica se o arquivo está vazio
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo vazio");
            }

            // Cria o diretório de uploads se não existir
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Gera o caminho completo do arquivo
            Path filePath = uploadPath.resolve(file.getOriginalFilename());

            // Salva o arquivo
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao enviar arquivo: " + e.getMessage());
        }
    }
}