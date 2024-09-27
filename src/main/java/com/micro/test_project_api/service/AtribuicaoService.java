package com.micro.test_project_api.service;

import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.repository.AtribuicaoRepository;

import java.util.Optional;

@Service
public class AtribuicaoService {

    private final AtribuicaoRepository atribuicaoRepository;

    public AtribuicaoService(AtribuicaoRepository atribuicaoRepository) {
        this.atribuicaoRepository = atribuicaoRepository;
    }

    public Page<Atribuicao> listAll(Pageable pageable) {
        return atribuicaoRepository.findAll(pageable);
    }

    public Optional<Atribuicao> findById(String id) {
        return atribuicaoRepository.findById(id);
    }

    public Atribuicao save(Atribuicao atribuicao) {
        return atribuicaoRepository.save(atribuicao);
    }

    public Atribuicao update(String id, Atribuicao newAtribuicao) {
        return atribuicaoRepository.findById(id)
            .map(existing -> {
                existing.setNome(newAtribuicao.getNome());
                existing.setSituacao(newAtribuicao.isSituacao());
                return atribuicaoRepository.save(existing);
            }).orElseThrow(() -> new EntityNotFoundException("Atribuição not found"));
    }

    public void deleteById(String id) {
        atribuicaoRepository.deleteById(id);
    }
}
