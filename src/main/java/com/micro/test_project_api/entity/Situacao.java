package com.micro.test_project_api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "situacao_cartorio")
public class Situacao {
    @Id
    @Column(length = 20, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


