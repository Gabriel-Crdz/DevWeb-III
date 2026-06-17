package com.example.ordem_servico_api.erros;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.ordem_servico_api.Exceptions.ResourceNotFoundException;

@RestControllerAdvice // Controller focada em dar avisos de erros
public class ManipuladorDeErros {
    
    /* Exemplo para UMA reposta de exceção, o correto e criar um metodo para cada Exceção possivel no sistema */
    @ExceptionHandler(Exception.class) 
    public ResponseEntity<RespostaErro> manipuladorExcecoes(Exception ex, WebRequest request){

        /* Mesagem de erro padrão */
        Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String erro = "Erro do Servidor";
        String mensagem = "Ocorreu um erro inesperado";

        /* Tratando diferentes exceções dentro do mesmo metodo (Apenas para fins academicos) */
        if(ex instanceof ResourceNotFoundException){
            status = HttpStatus.NOT_FOUND.value();
            erro = "Recurso não encontrado";
            mensagem = ex.getMessage();
        }
        else if(ex instanceof MethodArgumentNotValidException){
            status = HttpStatus.BAD_REQUEST.value();
            erro = "Recurso de validação";
            mensagem = ex.getMessage();
        }

        RespostaErro resposta = new RespostaErro(status, erro, mensagem, request.getDescription(false));

        /* Validação de uma Exceção especifica */
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) ex; // Transforma a exceção generica
            
            for(FieldError fieldError : validException.getBindingResult().getFieldErrors()){
                String campo = fieldError.getField();
                String msg = fieldError.getDefaultMessage();

                resposta.addErro(campo, msg);
            }
        
        }

        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
