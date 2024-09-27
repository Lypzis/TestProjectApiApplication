package com.micro.test_project_api.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.micro.test_project_api.entity.Situacao;
import com.micro.test_project_api.repository.SituacaoRepository;

import java.util.Optional;

@Service
public class SituacaoService {

    private final SituacaoRepository situacaoRepository;

    public SituacaoService(SituacaoRepository situacaoRepository) {
        this.situacaoRepository = situacaoRepository;
    }

    public Page<Situacao> listAll(Pageable pageable) {
        return situacaoRepository.findAll(pageable);
    }

    public Optional<Situacao> findById(String id) {
        return situacaoRepository.findById(id);
    }

    public Situacao save(Situacao situacao) {
        return situacaoRepository.save(situacao);
    }

    public Situacao update(String id, Situacao newSituacao) {
        return situacaoRepository.findById(id)
            .map(existing -> {
                existing.setNome(newSituacao.getNome());
                return situacaoRepository.save(existing);
            }).orElseThrow(() -> new EntityNotFoundException("Situação not found"));
    }

    public void deleteById(String id) {
        situacaoRepository.deleteById(id);
    }
}
