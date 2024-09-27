package com.micro.test_project_api.controller;

import org.springframework.dao.DataIntegrityViolationException;
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

import java.util.List;

import com.micro.test_project_api.entity.Atribuicao;
import com.micro.test_project_api.entity.Cartorio;
import com.micro.test_project_api.service.CartorioService;

@RestController
@RequestMapping("/api/cartorios")
public class CartorioController {
    private final CartorioService cartorioService;

    public CartorioController(CartorioService cartorioService) {
        this.cartorioService = cartorioService;
    }

    @GetMapping
    public Page<Cartorio> listAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cartorioService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cartorio> getById(@PathVariable int id) {
        return cartorioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cartorio> create(@RequestBody Cartorio cartorio) {
        Cartorio savedCartorio = cartorioService.save(cartorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCartorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cartorio> update(@PathVariable int id, @RequestBody Cartorio cartorio) {
        return ResponseEntity.ok(cartorioService.update(id, cartorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            cartorioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Registro utilizado em outro cadastro.");
        }
    }

    @PostMapping("/{cartorioId}/atribuicoes/{atribuicaoId}")
    public ResponseEntity<Void> linkAtribuicao(@PathVariable int cartorioId, @PathVariable String atribuicaoId) {
        cartorioService.linkAtribuicao(cartorioId, atribuicaoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cartorioId}/atribuicoes")
    public ResponseEntity<List<Atribuicao>> getAtribuicoesByCartorio(@PathVariable int cartorioId) {
        List<Atribuicao> atribuicoes = cartorioService.getAtribuicoesByCartorio(cartorioId);
        return ResponseEntity.ok(atribuicoes);
    }
}
