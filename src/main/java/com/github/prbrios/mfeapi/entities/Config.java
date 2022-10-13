package com.github.prbrios.mfeapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "config")
public class Config {
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "module")
    @Enumerated(EnumType.STRING)
    private ModuleType moduleType;

    @Column(name = "libsdir")
    private String libsDir;

    @Column(name = "codigoativacao")
    private String codigoAtivacao;

    @Column(name = "logativado")
    private boolean logAtivado;

}
