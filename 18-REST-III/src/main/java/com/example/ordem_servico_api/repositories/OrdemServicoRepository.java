package com.example.ordem_servico_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ordem_servico_api.entities.OrdemServico;
import java.util.List;


@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

    List<OrdemServico> findByCliente(Long id);

    List<OrdemServico> findByStatus(String status);
}
