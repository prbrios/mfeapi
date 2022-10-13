package com.github.prbrios.mfeapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.prbrios.mfeapi.controllers.dto.ConfigDTO;
import com.github.prbrios.mfeapi.entities.Config;
import com.github.prbrios.mfeapi.repositories.ConfigRepository;

@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    
    @Autowired
    private ConfigRepository configRepository;

    @GetMapping
    public ResponseEntity<Config> get() {
        Config config = this.configRepository.findAll().get(0);
        return ResponseEntity.ok(config);
    }

    @PostMapping
    public ResponseEntity<Config> post(@Valid @RequestBody ConfigDTO dto) {
        
        Config config = new Config();
        config.setId(dto.getId());
        config.setCodigoAtivacao(dto.getCodigoAtivacao());
        config.setLibsDir(dto.getLibsDir());
        config.setLogAtivado(dto.getLogAtivado());

        config = this.configRepository.save(config);
        return ResponseEntity.ok(config);
    }

}
