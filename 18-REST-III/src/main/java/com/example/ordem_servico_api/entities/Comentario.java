package com.example.ordem_servico_api.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comentarios")
@Data
@NoArgsConstructor
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_id")
    // @JsonIgnore // Impede uma referencia ciclica(ordem serviço chma comentario e o comentario chama a ordem serviço)
    @JsonBackReference // A associação é referenciada pelo ordem serviço
    OrdemServico ordemServico;

    @CreationTimestamp
    private LocalDateTime craatedAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
