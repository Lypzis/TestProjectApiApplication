package com.micro.test_project_api.repository;

import com.micro.test_project_api.entity.Atribuicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtribuicaoRepository extends JpaRepository<Atribuicao, String> {
}
