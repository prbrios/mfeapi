package com.github.prbrios.mfeapi.controllers.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

public class CancelaUltimaVendaDTO extends BaseDTO {
    
    @Getter
    @NotNull
    @Pattern(regexp = "^CFe[0-9]{44}$")
    private String chave;

    @Getter
    @NotNull
    @NotEmpty
    private String dadosCancelamento;
}
