package com.github.prbrios.mfeapi.controllers.dto;

import lombok.Data;

@Data
public class RetornoConsultaStatusOperacional {
    private String numeroSessao;
    private String eeeee;
    private String mensagem;
    private String cod;
    private String mensagemSEFAZ;
    private RetornoConsultaStatusOperacional.ConteudoRetorno conteudoRetorno;
    private String retornoMFE;

    @Data
    public static class ConteudoRetorno {
        private String nserie;
        private String tipoLan;
        private String lanIP;
        private String lanMac;
        private String lanMasc;
        private String lanGw;
        private String lanDns1;
        private String lanDns2;
        private String statusLan;
        private String nivelBateria;
        private String mtTotal;
        private String mtUsada;
        private String dhAtual;
        private String verSb;
        private String verLayout;
        private String ultimoCFeSat;
        private String listaInicial;
        private String listaFinal;
        private String dhCfe;
        private String dhUltima;
        private String certEmissao;
        private String certVencimento;
        private String estadoOperacao;
    }
}
