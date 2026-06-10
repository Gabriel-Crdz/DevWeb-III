package com.example.ordem_servico_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordem_servico_api.Exceptions.ResourceNotFoundException;
import com.example.ordem_servico_api.entities.Comentario;
import com.example.ordem_servico_api.repositories.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    public List<Comentario> findAll() {
       
        List<Comentario> comentarios = comentarioRepository.findAll();

        return comentarios;
    }

    public Comentario findById(Long id){
        return comentarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Comentario", id));
    }

    public Comentario save(Comentario comentario) {

        if(comentario.getDescricao() == null || comentario.getDescricao().trim().isEmpty()){
            throw new IllegalArgumentException("A Descrição não pode ser vazio!!");
        }

        if(comentario.getDescricao().trim().length() < 5){
            throw new IllegalArgumentException("A Descrição deve ter mais que 5 caracteres!!");
        }

        return comentarioRepository.save(comentario);
    }

    public Comentario update(Long id, Comentario comentario) {
        Comentario comentarioExistente = comentarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Comentario", id));

        comentarioExistente.setDescricao(comentario.getDescricao());

        return comentarioRepository.save(comentarioExistente);
    }
    
    public void delete(Long id) {
        comentarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Comentario", id));

        comentarioRepository.deleteById(id);
    }
}
