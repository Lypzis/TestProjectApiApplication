package com.micro.test_project_api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.entity.Cartorio;
import com.micro.test_project_api.repository.AtribuicaoRepository;
import com.micro.test_project_api.repository.CartorioRepository;

class CartorioServiceTest {

    @Mock
    private CartorioRepository cartorioRepository;

    @Mock
    private AtribuicaoRepository atribuicaoRepository;

    @InjectMocks
    private CartorioService cartorioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testListAll() {
        Page<Cartorio> pagedResult = new PageImpl<>(Arrays.asList(new Cartorio()));
        when(cartorioRepository.findAll(any(PageRequest.class))).thenReturn(pagedResult);

        Page<Cartorio> result = cartorioService.listAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testFindById_Success() {
        Cartorio cartorio = new Cartorio();
        cartorio.setId(1);
        when(cartorioRepository.findById(1)).thenReturn(Optional.of(cartorio));

        Optional<Cartorio> result = cartorioService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        when(cartorioRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Cartorio> result = cartorioService.findById(1);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_NewCartorio() {
        Cartorio cartorio = new Cartorio();
        cartorio.setId(1);
        when(cartorioRepository.existsById(1)).thenReturn(false);
        when(cartorioRepository.save(any(Cartorio.class))).thenReturn(cartorio);

        Cartorio result = cartorioService.save(cartorio);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdate() {
        Cartorio existingCartorio = new Cartorio();
        existingCartorio.setId(1);
        existingCartorio.setNome("Old Name");

        Cartorio updatedCartorio = new Cartorio();
        updatedCartorio.setNome("New Name");

        when(cartorioRepository.findById(1)).thenReturn(Optional.of(existingCartorio));
        when(cartorioRepository.save(any(Cartorio.class))).thenReturn(existingCartorio);

        Cartorio result = cartorioService.update(1, updatedCartorio);

        assertEquals("New Name", result.getNome());
    }

    @Test
    void testDeleteById() {
        doNothing().when(cartorioRepository).deleteById(1);
        
        cartorioService.deleteById(1);

        verify(cartorioRepository, times(1)).deleteById(1);
    }
    @Test
    void testLinkAtribuicao() {
        Cartorio cartorio = new Cartorio();
        cartorio.setId(1);
        cartorio.setAtribuicoes(new ArrayList<>());

        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");

        when(cartorioRepository.findById(1)).thenReturn(Optional.of(cartorio));
        when(atribuicaoRepository.findById("ATRIB_001")).thenReturn(Optional.of(atribuicao));

        cartorioService.linkAtribuicao(1, "ATRIB_001");

        assertTrue(cartorio.getAtribuicoes().contains(atribuicao));

        verify(cartorioRepository).save(cartorio);
    }

    @Test
    void testGetAtribuicoesByCartorio() {
        Cartorio cartorio = new Cartorio();
        cartorio.setId(1);
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");
        cartorio.setAtribuicoes(Arrays.asList(atribuicao));

        when(cartorioRepository.findById(1)).thenReturn(Optional.of(cartorio));

        List<Atribuicao> atribuicoes = cartorioService.getAtribuicoesByCartorio(1);

        assertEquals(1, atribuicoes.size());
        assertEquals("ATRIB_001", atribuicoes.get(0).getId());
    }
}
