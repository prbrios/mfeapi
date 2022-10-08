package com.github.prbrios.mfeapi.controllers.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ConfigDTO {
    
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String codigoAtivacao;

    @NotNull
    @NotEmpty
    private String libsDir;

}
