package com.github.prbrios.mfeapi.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Falha ao tentar integrar/comunicar com o modulo fiscal")
public class IntegracaoException extends RuntimeException {

    public IntegracaoException(){
        super();
    }

    public IntegracaoException(String message){
        super(message);
    }
}
