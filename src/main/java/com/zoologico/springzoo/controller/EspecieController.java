package com.zoologico.springzoo.controller;

import com.zoologico.springzoo.dto.EspecieRequest;
import com.zoologico.springzoo.dto.EspecieResponse;
import com.zoologico.springzoo.service.EspecieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especies")
public class EspecieController {

    private final EspecieService especieService;

    public EspecieController(EspecieService especieService){
        this.especieService = especieService;
    }

    @PostMapping
    public ResponseEntity<EspecieResponse> criar(@RequestBody @Valid EspecieRequest request){
        EspecieResponse response = especieService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EspecieResponse>> listarTodas(){
        List<EspecieResponse> response = especieService.listarTodas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieResponse> buscarPorId(@PathVariable Long id){
        EspecieResponse response = especieService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecieResponse> atualizar(@PathVariable Long id, @RequestBody @Valid EspecieRequest request){
        EspecieResponse response = especieService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

}
