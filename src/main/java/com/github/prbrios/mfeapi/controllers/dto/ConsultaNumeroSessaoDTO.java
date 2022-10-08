package com.github.prbrios.mfeapi.controllers.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;

public class ConsultaNumeroSessaoDTO extends BaseDTO {

    @Getter
    @NotNull
    @Min(100000)
    @Max(888889)
    private Integer numeroSessaoConsulta;

}
