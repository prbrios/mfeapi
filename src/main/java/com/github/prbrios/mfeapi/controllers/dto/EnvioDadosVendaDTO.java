package com.github.prbrios.mfeapi.controllers.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class EnvioDadosVendaDTO extends BaseDTO {

    @JsonProperty("dadosVenda")
    @Setter
    @Getter
    @NotNull
    private String dadosVenda;

}
