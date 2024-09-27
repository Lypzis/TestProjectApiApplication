package com.micro.test_project_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cartorio")
public class Cartorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 250)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "situacao_id", nullable = false)
    private Situacao situacaoCartorio;

    @ManyToMany
    @JoinTable(
      name = "cartorio_atribuicoes",
      joinColumns = @JoinColumn(name = "cartorio_id"),
      inverseJoinColumns = @JoinColumn(name = "atribuicao_id"))
    private List<Atribuicao> atribuicoes;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Atribuicao> getAtribuicoes() {
        return atribuicoes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtribuicoes(List<Atribuicao> atribuicoes) {
        this.atribuicoes = atribuicoes;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Situacao getSituacaoCartorio() {
        return situacaoCartorio;
    }

    public void setSituacaoCartorio(Situacao situacaoCartorio) {
        this.situacaoCartorio = situacaoCartorio;
    }

}
