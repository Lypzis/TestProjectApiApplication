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

import com.micro.test_project_api.entity.Situacao;
import com.micro.test_project_api.repository.SituacaoRepository;

class SituacaoServiceTest {

    @Mock
    private SituacaoRepository situacaoRepository;

    @InjectMocks
    private SituacaoService situacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
    }

    @Test
    void testListAll() {
        Page<Situacao> pagedResult = new PageImpl<>(Arrays.asList(new Situacao()));
        when(situacaoRepository.findAll(any(PageRequest.class))).thenReturn(pagedResult);

        Page<Situacao> result = situacaoService.listAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testFindById_Success() {
        Situacao situacao = new Situacao();
        situacao.setId("SIT_ATIVO");
        when(situacaoRepository.findById("SIT_ATIVO")).thenReturn(Optional.of(situacao));

        Optional<Situacao> result = situacaoService.findById("SIT_ATIVO");

        assertTrue(result.isPresent());
        assertEquals("SIT_ATIVO", result.get().getId());
    }

    @Test
    void testFindById_NotFound() {
        when(situacaoRepository.findById("SIT_ATIVO")).thenReturn(Optional.empty());

        Optional<Situacao> result = situacaoService.findById("SIT_ATIVO");

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_NewSituacao() {
        Situacao situacao = new Situacao();
        situacao.setId("SIT_ATIVO");
        when(situacaoRepository.save(any(Situacao.class))).thenReturn(situacao);

        Situacao result = situacaoService.save(situacao);

        assertNotNull(result);
        assertEquals("SIT_ATIVO", result.getId());
    }

    @Test
    void testUpdate() {
        Situacao existingSituacao = new Situacao();
        existingSituacao.setId("SIT_ATIVO");
        existingSituacao.setNome("Old Name");

        Situacao updatedSituacao = new Situacao();
        updatedSituacao.setNome("New Name");

        when(situacaoRepository.findById("SIT_ATIVO")).thenReturn(Optional.of(existingSituacao));
        when(situacaoRepository.save(any(Situacao.class))).thenReturn(existingSituacao);

        Situacao result = situacaoService.update("SIT_ATIVO", updatedSituacao);

        assertEquals("New Name", result.getNome());
    }

    @Test
    void testDeleteById() {
        doNothing().when(situacaoRepository).deleteById("SIT_ATIVO");

        situacaoService.deleteById("SIT_ATIVO");

        verify(situacaoRepository, times(1)).deleteById("SIT_ATIVO");
    }
}
