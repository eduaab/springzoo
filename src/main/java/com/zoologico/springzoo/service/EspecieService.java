package com.zoologico.springzoo.service;

import com.zoologico.springzoo.dto.EspecieRequest;
import com.zoologico.springzoo.dto.EspecieResponse;
import com.zoologico.springzoo.model.Especie;
import com.zoologico.springzoo.repository.EspecieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieService(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    @Transactional
    public EspecieResponse criar(EspecieRequest request) {
        if (especieRepository.existsByNomeCientifico(request.nomeCientifico())) {
            throw new RuntimeException("Já existe uma espécie com esse nome científico");
        }

        Especie especie = new Especie();
        especie.setNomePopular(request.nomePopular());
        especie.setNomeCientifico(request.nomeCientifico());

        Especie salva = especieRepository.save(especie);

        return toResponse(salva);
    }

    @Transactional(readOnly = true)
    public List<EspecieResponse> listarTodas() {
        return especieRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EspecieResponse buscarPorId(Long id) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));

        return toResponse(especie);
    }

    @Transactional
    public EspecieResponse atualizar(Long id, EspecieRequest request) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));

        if (!especie.getNomeCientifico().equals(request.nomeCientifico())
                && especieRepository.existsByNomeCientifico(request.nomeCientifico())) {
            throw new RuntimeException("Já existe uma espécie com esse nome científico");
        }

        especie.setNomePopular(request.nomePopular());
        especie.setNomeCientifico(request.nomeCientifico());

        Especie salva = especieRepository.save(especie);

        return toResponse(salva);
    }

    private EspecieResponse toResponse(Especie especie) {
        return new EspecieResponse(
                especie.getId(),
                especie.getNomePopular(),
                especie.getNomeCientifico()
        );
    }
}