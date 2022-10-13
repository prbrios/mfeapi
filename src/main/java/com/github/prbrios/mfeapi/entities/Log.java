package com.github.prbrios.mfeapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "log")
public class Log {
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numsessao")
    private Integer numeroSessao;

    @Column(name = "xmlenvio")
    private String xmlEnvio;

    @Column(name = "retornomodulo")
    private String retornoModulo;

    @Column(name = "dtenvio")
    private LocalDateTime dataEnvio;

    @Column(name = "dtretorno")
    private LocalDateTime dataRetorno;
}
