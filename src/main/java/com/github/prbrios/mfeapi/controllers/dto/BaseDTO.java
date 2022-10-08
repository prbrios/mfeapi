package com.github.prbrios.mfeapi.controllers.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class BaseDTO {
    
    @JsonProperty("numeroSessao")
    @Setter
    @Getter
    @NotNull
    @Min(100000)
    @Max(888889)
    private Integer numeroSessao;

}
