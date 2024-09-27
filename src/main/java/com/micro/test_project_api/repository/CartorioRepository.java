package com.micro.test_project_api.repository;

import com.micro.test_project_api.entity.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartorioRepository extends JpaRepository<Cartorio, Integer> {
    
}
