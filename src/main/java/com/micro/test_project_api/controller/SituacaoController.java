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

import com.micro.test_project_api.entity.Situacao;
import com.micro.test_project_api.service.SituacaoService;

@RestController
@RequestMapping("/api/situacoes")
public class SituacaoController {

    private final SituacaoService situacaoService;

    public SituacaoController(SituacaoService situacaoService) {
        this.situacaoService = situacaoService;
    }

    @GetMapping
    public Page<Situacao> listAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return situacaoService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Situacao> getById(@PathVariable String id) {
        return situacaoService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Situacao> create(@RequestBody Situacao situacao) {
        Situacao savedSituacao = situacaoService.save(situacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSituacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Situacao> update(@PathVariable String id, @RequestBody Situacao situacao) {
        return ResponseEntity.ok(situacaoService.update(id, situacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        situacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

