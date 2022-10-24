package com.github.prbrios.mfeapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.prbrios.mfeapi.controllers.dto.CancelaUltimaVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.ConsultaNumeroSessaoDTO;
import com.github.prbrios.mfeapi.controllers.dto.EnvioDadosVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoCancelaUltimaVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoConsultaNumeroSessaoDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoConsultaStatusOperacional;
import com.github.prbrios.mfeapi.controllers.dto.RetornoEnvioDadosVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoExtrairLogsDTO;
import com.github.prbrios.mfeapi.services.MainService;

@RestController
public class MainController {
    
    @Autowired
    private MainService service;

    @Value("${build.version}")
    private String versao;

    @GetMapping(value = "versao", produces = {"text/plain"})
    public ResponseEntity<String> get() {
        return ResponseEntity.ok(this.versao);
    }
    
    @PostMapping(value = "consultar-numero-sessao", consumes = {"application/json"})
    public ResponseEntity<RetornoConsultaNumeroSessaoDTO> consultarSessao(@Valid @RequestBody ConsultaNumeroSessaoDTO consultaNumeroSessaoDTO) {
        return ResponseEntity.ok(this.service.consultarNumeroSessao(consultaNumeroSessaoDTO));
    }

    @PostMapping(value = "extrair-logs")
    public ResponseEntity<RetornoExtrairLogsDTO> extrairLogs() {
        return ResponseEntity.ok(this.service.extrairLogs());
    }

    @GetMapping(value = "info", produces = {"application/json"})
    public ResponseEntity<java.util.Map<String, String>> getOSinfo() {
        java.util.Map<String, String> ret = new java.util.HashMap<>();
        ret.put("javaVersion", System.getProperty("java.version"));
        ret.put("sunArchDataModel", System.getProperty("sun.arch.data.model"));
        ret.put("osArch", System.getProperty("os.arch"));

        return ResponseEntity.ok(ret);
    }

    @PostMapping(value = "enviar-dados-venda", consumes = {"application/json"})
    public ResponseEntity<RetornoEnvioDadosVendaDTO> enviarDadosVenda(@Valid @RequestBody EnvioDadosVendaDTO envioDadosVenda) {
        return ResponseEntity.ok(this.service.enviarDadosVenda(envioDadosVenda));
    }

    @PostMapping(value = "cancelar-ultima-venda", consumes = {"application/json"})
    public ResponseEntity<RetornoCancelaUltimaVendaDTO> cancelarUltimaVenda(@Valid @RequestBody CancelaUltimaVendaDTO cancelaUltimaVendaDTO) {
        return ResponseEntity.ok(this.service.cancelarUltimaVenda(cancelaUltimaVendaDTO));
    }

    @PostMapping("consultar-status-operacional")
    public ResponseEntity<RetornoConsultaStatusOperacional> consultarStatusOperacional() {
        return ResponseEntity.ok(this.service.consultarStatusOperacional());
    }

}
