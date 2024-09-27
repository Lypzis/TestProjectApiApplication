package com.micro.test_project_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.service.AtribuicaoService;

@RestController
@RequestMapping("/api/atribuicoes")
public class AtribuicaoController {

    private final AtribuicaoService atribuicaoService;

    public AtribuicaoController(AtribuicaoService atribuicaoService) {
        this.atribuicaoService = atribuicaoService;
    }

    @GetMapping
    public Page<Atribuicao> listAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return atribuicaoService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atribuicao> getById(@PathVariable String id) {
        return atribuicaoService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atribuicao> create(@RequestBody Atribuicao atribuicao) {
        Atribuicao savedAtribuicao = atribuicaoService.save(atribuicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAtribuicao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atribuicao> update(@PathVariable String id, @RequestBody Atribuicao atribuicao) {
        return ResponseEntity.ok(atribuicaoService.update(id, atribuicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        atribuicaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
