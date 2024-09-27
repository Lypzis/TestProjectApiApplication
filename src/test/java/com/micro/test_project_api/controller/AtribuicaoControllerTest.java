package com.micro.test_project_api.controller;

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

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.service.AtribuicaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class AtribuicaoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AtribuicaoService atribuicaoService;

    @InjectMocks
    private AtribuicaoController atribuicaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(atribuicaoController).build();
    }

    @Test
    void testGetById() throws Exception {
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");
        when(atribuicaoService.findById("ATRIB_001")).thenReturn(Optional.of(atribuicao));

        mockMvc.perform(get("/api/atribuicoes/ATRIB_001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ATRIB_001"));
    }

    @Test
    void testCreate() throws Exception {
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");
        when(atribuicaoService.save(any(Atribuicao.class))).thenReturn(atribuicao);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(atribuicao);

        mockMvc.perform(post("/api/atribuicoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("ATRIB_001"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(atribuicaoService).deleteById("ATRIB_001");

        mockMvc.perform(delete("/api/atribuicoes/ATRIB_001"))
                .andExpect(status().isNoContent());

        verify(atribuicaoService, times(1)).deleteById("ATRIB_001");
    }
}
