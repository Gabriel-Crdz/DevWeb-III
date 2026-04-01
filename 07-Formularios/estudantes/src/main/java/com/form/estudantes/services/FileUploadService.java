package com.form.estudantes.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service // Notação para informa ao spring que essa classe é um service
public class FileUploadService {
    public String upload(MultipartFile arq)  throws IOException{
        /* Salvando um arquivo dentro da aplicação */
        String pastaUploads = "src/main/resources/static/uploads/"; // URL da pasta onde será salva

        String nomeArquivo = UUID.randomUUID() + "-" + arq.getOriginalFilename();

        Path path = Paths.get(pastaUploads + nomeArquivo);

        Files.createDirectories(path.getParent()); // Cria a pasta caso ela não exista

        Files.copy(arq.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING); // Copia os dados do arquivo para o caminho. Caso a imagem exista ela sera subscrita
        
        return nomeArquivo;
    }
}
