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

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.entity.Cartorio;
import com.micro.test_project_api.service.CartorioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class CartorioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartorioService cartorioService;

    @InjectMocks
    private CartorioController cartorioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartorioController).build();
    }

    @Test
    void testGetById() throws Exception {
        Cartorio cartorio = new Cartorio();
        cartorio.setId(1);
        when(cartorioService.findById(1)).thenReturn(Optional.of(cartorio));

        mockMvc.perform(get("/api/cartorios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testCreate() throws Exception {
        Cartorio cartorio = new Cartorio();
        cartorio.setId(1);
        when(cartorioService.save(any(Cartorio.class))).thenReturn(cartorio);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cartorio);

        mockMvc.perform(post("/api/cartorios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(cartorioService).deleteById(1);

        mockMvc.perform(delete("/api/cartorios/1"))
                .andExpect(status().isNoContent());

        verify(cartorioService, times(1)).deleteById(1);
    }

    @Test
    void testLinkAtribuicao() throws Exception {
        mockMvc.perform(post("/api/cartorios/1/atribuicoes/ATRIB_001"))
                .andExpect(status().isNoContent());

        verify(cartorioService, times(1)).linkAtribuicao(1, "ATRIB_001");
    }

    @Test
    void testGetAtribuicoesByCartorio() throws Exception {
        // Mock returned data
        Atribuicao atribuicao = new Atribuicao();
        atribuicao.setId("ATRIB_001");
        when(cartorioService.getAtribuicoesByCartorio(1)).thenReturn(Arrays.asList(atribuicao));

        // Perform the GET request to retrieve atribuicoes
        mockMvc.perform(get("/api/cartorios/1/atribuicoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("ATRIB_001"));

        // Verify that the service was called
        verify(cartorioService, times(1)).getAtribuicoesByCartorio(1);
    }
}
