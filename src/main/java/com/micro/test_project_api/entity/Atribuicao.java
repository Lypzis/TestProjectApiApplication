package com.micro.test_project_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atribuicao_cartorio")
public class Atribuicao {
    @Id
    @Column(length = 20, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean situacao = true;

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

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
}

