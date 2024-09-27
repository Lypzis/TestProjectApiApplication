package com.micro.test_project_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.micro.test_project_api.entity.Situacao;
import com.micro.test_project_api.service.SituacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class SituacaoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SituacaoService situacaoService;

    @InjectMocks
    private SituacaoController situacaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(situacaoController).build();
    }

    @Test
    void testGetById() throws Exception {
        Situacao situacao = new Situacao();
        situacao.setId("SIT_ATIVO");
        when(situacaoService.findById("SIT_ATIVO")).thenReturn(Optional.of(situacao));

        mockMvc.perform(get("/api/situacoes/SIT_ATIVO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("SIT_ATIVO"));
    }

    @Test
    void testCreate() throws Exception {
        Situacao situacao = new Situacao();
        situacao.setId("SIT_ATIVO");
        when(situacaoService.save(any(Situacao.class))).thenReturn(situacao);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(situacao);

        mockMvc.perform(post("/api/situacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("SIT_ATIVO"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(situacaoService).deleteById("SIT_ATIVO");

        mockMvc.perform(delete("/api/situacoes/SIT_ATIVO"))
                .andExpect(status().isNoContent());

        verify(situacaoService, times(1)).deleteById("SIT_ATIVO");
    }
}
