package com.example.ordem_servico_api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Retorna um 404: Not Found, em vez do 500: Server Error
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource, Long id){
        super(String.format("%s com o id: %d não encontrado!", resource, id));
    }
}
