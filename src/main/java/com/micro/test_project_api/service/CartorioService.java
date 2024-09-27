package com.micro.test_project_api.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.entity.Cartorio;
import com.micro.test_project_api.repository.AtribuicaoRepository;
import com.micro.test_project_api.repository.CartorioRepository;

@Service
public class CartorioService {
    private final CartorioRepository cartorioRepository;
    private final AtribuicaoRepository atribuicaoRepository;

    public CartorioService(CartorioRepository cartorioRepository, AtribuicaoRepository atribuicaoRepository) {
        this.cartorioRepository = cartorioRepository;
        this.atribuicaoRepository = atribuicaoRepository; 
    }

    public Page<Cartorio> listAll(Pageable pageable) {
        return cartorioRepository.findAll(pageable);
    }

    public Optional<Cartorio> findById(int id) {
        return cartorioRepository.findById(id);
    }

    public Cartorio save(Cartorio cartorio) {
        if (cartorioRepository.existsById(cartorio.getId())) {
            throw new IllegalArgumentException("Registro já cadastrado");
        }
        return cartorioRepository.save(cartorio);
    }

    public Cartorio update(int id, Cartorio newCartorio) {
        Cartorio existing = cartorioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado"));
        existing.setNome(newCartorio.getNome());
        existing.setAtribuicoes(newCartorio.getAtribuicoes());
        return cartorioRepository.save(existing);
    }

    public void deleteById(int id) {
        cartorioRepository.deleteById(id);
    }

    public void linkAtribuicao(int cartorioId, String atribuicaoId) {
        Cartorio cartorio = cartorioRepository.findById(cartorioId)
                .orElseThrow(() -> new EntityNotFoundException("Cartório not found"));
        Atribuicao atribuicao = atribuicaoRepository.findById(atribuicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Atribuição not found"));

        cartorio.getAtribuicoes().add(atribuicao);
        cartorioRepository.save(cartorio);
    }

    public List<Atribuicao> getAtribuicoesByCartorio(int cartorioId) {
        Cartorio cartorio = cartorioRepository.findById(cartorioId)
                .orElseThrow(() -> new EntityNotFoundException("Cartório not found"));
        return cartorio.getAtribuicoes();
    }
}
