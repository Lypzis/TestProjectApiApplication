package com.micro.test_project_api.repository;

import com.micro.test_project_api.entity.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoRepository extends JpaRepository<Situacao, String> {
    
}
