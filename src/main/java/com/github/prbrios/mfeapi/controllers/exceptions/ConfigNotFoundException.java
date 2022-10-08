package com.github.prbrios.mfeapi.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Configura\u00e7\u00e3o nao definida")
public class ConfigNotFoundException extends RuntimeException {

}
