package com.micro.test_project_api.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.repository.AtribuicaoRepository;

class AtribuicaoServiceTest {

    @Mock
    private AtribuicaoRepository atribuicaoRepository;

    @InjectMocks
    private AtribuicaoService atribuicaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Page<Atribuicao> pagedResult = new PageImpl<>(Arrays.asList(new Atribuicao()));
        when(atribuicaoRepository.findAll(any(PageRequest.class))).thenReturn(pagedResult);

        Page<Atribuicao> result = atribuicaoService.listAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testFindById_Success() {
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");
        when(atribuicaoRepository.findById("ATRIB_001")).thenReturn(Optional.of(atribuicao));

        Optional<Atribuicao> result = atribuicaoService.findById("ATRIB_001");

        assertTrue(result.isPresent());
        assertEquals("ATRIB_001", result.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        when(atribuicaoRepository.findById("ATRIB_001")).thenReturn(Optional.empty());

        Optional<Atribuicao> result = atribuicaoService.findById("ATRIB_001");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_NewAtribuicao() {
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");
        when(atribuicaoRepository.save(any(Atribuicao.class))).thenReturn(atribuicao);

        Atribuicao result = atribuicaoService.save(atribuicao);

        assertNotNull(result);
        assertEquals("ATRIB_001", result.getId());
    }

    @Test
    void testUpdate() {
        Atribuicao existingAtribuicao = new Atribuicao();
        existingAtribuicao.setId("ATRIB_001");
        existingAtribuicao.setNome("Old Name");

        Atribuicao updatedAtribuicao = new Atribuicao();
        updatedAtribuicao.setNome("New Name");

        when(atribuicaoRepository.findById("ATRIB_001")).thenReturn(Optional.of(existingAtribuicao));
        when(atribuicaoRepository.save(any(Atribuicao.class))).thenReturn(existingAtribuicao);

        Atribuicao result = atribuicaoService.update("ATRIB_001", updatedAtribuicao);

        assertEquals("New Name", result.getNome());
    }

    @Test
    void testDeleteById() {
        doNothing().when(atribuicaoRepository).deleteById("ATRIB_001");

        atribuicaoService.deleteById("ATRIB_001");

        verify(atribuicaoRepository, times(1)).deleteById("ATRIB_001");
    }
}
