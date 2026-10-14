package com.zoologico.springzoo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecieRequest(
    @NotBlank(message = "O nome popular não pode ser vazio")
    @Size(max = 100)
    String nomePopular,

    @NotBlank(message = "O nome científico não pode ser vazio")
    @Size(max = 100)
    String nomeCientifico
){}