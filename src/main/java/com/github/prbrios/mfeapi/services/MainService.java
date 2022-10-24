package com.github.prbrios.mfeapi.services;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.prbrios.mfeapi.Utils;
import com.github.prbrios.mfeapi.controllers.dto.CancelaUltimaVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.ConsultaNumeroSessaoDTO;
import com.github.prbrios.mfeapi.controllers.dto.EnvioDadosVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoCancelaUltimaVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoConsultaNumeroSessaoDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoConsultaStatusOperacional;
import com.github.prbrios.mfeapi.controllers.dto.RetornoEnvioDadosVendaDTO;
import com.github.prbrios.mfeapi.controllers.dto.RetornoExtrairLogsDTO;
import com.github.prbrios.mfeapi.controllers.exceptions.ConfigNotFoundException;
import com.github.prbrios.mfeapi.controllers.exceptions.IntegracaoException;
import com.github.prbrios.mfeapi.entities.Config;
import com.github.prbrios.mfeapi.entities.Log;
import com.github.prbrios.mfeapi.repositories.ConfigRepository;
import com.github.prbrios.mfeapi.repositories.LogRepository;

import br.com.unigex.integrador.mfe.IntegradorMFEDireto;

@Service
public class MainService {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private LogRepository logRepository;

    public RetornoConsultaNumeroSessaoDTO consultarNumeroSessao(ConsultaNumeroSessaoDTO obj) {
        Config config = this.getConfig();
        IntegradorMFEDireto integrador = new IntegradorMFEDireto();
        try {
            String retornoModulo = integrador.consultarNumeroSessao(obj.getNumeroSessao(), config.getCodigoAtivacao(), obj.getNumeroSessaoConsulta(), config.getLibsDir());
            String[] retornoModuloArr = retornoModulo.split("[|]");

            RetornoConsultaNumeroSessaoDTO retorno = new RetornoConsultaNumeroSessaoDTO();
            retorno.setNumeroSessao(retornoModuloArr.length >= 1 ? retornoModuloArr[0] : null);
            retorno.setEeeee(retornoModuloArr.length >= 2 ? retornoModuloArr[1] : null);
            retorno.setCccc(retornoModuloArr.length >= 3 ? retornoModuloArr[2] : null);
            retorno.setMensagem(retornoModuloArr.length >= 4 ? retornoModuloArr[3] : null);
            retorno.setCod(retornoModuloArr.length >= 5 ? retornoModuloArr[4] : null);
            retorno.setMensagemSEFAZ(retornoModuloArr.length >= 6 ? retornoModuloArr[5] : null);
            retorno.setArquivoBase64(retornoModuloArr.length >= 7 ? retornoModuloArr[6] : null);
            retorno.setTimestamp(retornoModuloArr.length >= 8 ? retornoModuloArr[7] : null);
            retorno.setChaveConsulta(retornoModuloArr.length >= 9 ? retornoModuloArr[8] : null);
            retorno.setValorTotalCFe(retornoModuloArr.length >= 10 ? retornoModuloArr[9] : null);
            retorno.setCPFCNPJValue(retornoModuloArr.length >= 11 ? retornoModuloArr[10] : null);
            retorno.setAssinaturaQRCODE(retornoModuloArr.length >= 12 ? retornoModuloArr[11] : null);
            retorno.setRetornoMFE(retornoModulo);

            return retorno;
        } catch (UnsupportedEncodingException e) {
            throw new IntegracaoException();
        }
    }

    public RetornoEnvioDadosVendaDTO enviarDadosVenda(EnvioDadosVendaDTO obj) {
        Config config = this.getConfig();

        boolean isLogAtivado = config.isLogAtivado();
        Log log = new Log();
        if (isLogAtivado) {
            log.setDataEnvio(LocalDateTime.now());
            log.setNumeroSessao(obj.getNumeroSessao());
            log.setXmlEnvio(obj.getDadosVenda());
            log = this.logRepository.save(log);
        }

        IntegradorMFEDireto integrador = new IntegradorMFEDireto();
        try {
            String retornoModulo = integrador.enviarDadosVenda(obj.getNumeroSessao(), config.getCodigoAtivacao(), obj.getDadosVenda(), config.getLibsDir());
            if (retornoModulo.equals("")) {
                throw new Exception();
            }

            String[] retornoModuloArr = retornoModulo.split("[|]");

            RetornoEnvioDadosVendaDTO retorno = new RetornoEnvioDadosVendaDTO();
            retorno.setNumeroSessao(retornoModuloArr.length >= 1 ? retornoModuloArr[0] : null);
            retorno.setEeeee(retornoModuloArr.length >= 2 ? retornoModuloArr[1] : null);
            retorno.setCccc(retornoModuloArr.length >= 3 ? retornoModuloArr[2] : null);
            retorno.setMensagem(retornoModuloArr.length >= 4 ? retornoModuloArr[3] : null);
            retorno.setCod(retornoModuloArr.length >= 5 ? retornoModuloArr[4] : null);
            retorno.setMensagemSEFAZ(retornoModuloArr.length >= 6 ? retornoModuloArr[5] : null);
            retorno.setArquivoBase64(retornoModuloArr.length >= 7 ? retornoModuloArr[6] : null);
            retorno.setTimestamp(retornoModuloArr.length >= 8 ? retornoModuloArr[7] : null);
            retorno.setChaveConsulta(retornoModuloArr.length >= 9 ? retornoModuloArr[8] : null);
            retorno.setValorTotalCFe(retornoModuloArr.length >= 10 ? retornoModuloArr[9] : null);
            retorno.setCPFCNPJValue(retornoModuloArr.length >= 11 ? retornoModuloArr[10] : null);
            retorno.setAssinaturaQRCODE(retornoModuloArr.length >= 12 ? retornoModuloArr[11] : null);
            retorno.setRetornoMFE(retornoModulo);
            
            if (isLogAtivado && log.getId() != null) {
                log.setDataRetorno(LocalDateTime.now());
                log.setRetornoModulo(retornoModulo);
                this.logRepository.save(log);
            }

            return retorno;
        } catch (Exception e) {
            throw new IntegracaoException();
        }
    }

    public RetornoCancelaUltimaVendaDTO cancelarUltimaVenda(CancelaUltimaVendaDTO obj) {
        Config config = this.getConfig();

        boolean isLogAtivado = config.isLogAtivado();
        Log log = new Log();
        if (isLogAtivado) {
            log.setDataEnvio(LocalDateTime.now());
            log.setNumeroSessao(obj.getNumeroSessao());
            log.setXmlEnvio(obj.getDadosCancelamento());
            log = this.logRepository.save(log);
        }

        IntegradorMFEDireto integrador = new IntegradorMFEDireto();
        try {
            String retornoModulo = integrador.cancelarUltimaVenda(obj.getNumeroSessao(), config.getCodigoAtivacao(), obj.getChave(), obj.getDadosCancelamento(), config.getLibsDir());
            if (retornoModulo.equals("")) {
                throw new Exception();
            }

            String[] retornoModuloArr = retornoModulo.split("[|]");

            RetornoCancelaUltimaVendaDTO retorno = new RetornoCancelaUltimaVendaDTO();
            retorno.setNumeroSessao(retornoModuloArr.length >= 1 ? retornoModuloArr[0] : null);
            retorno.setEeeee(retornoModuloArr.length >= 2 ? retornoModuloArr[1] : null);
            retorno.setCccc(retornoModuloArr.length >= 3 ? retornoModuloArr[2] : null);
            retorno.setMensagem(retornoModuloArr.length >= 4 ? retornoModuloArr[3] : null);
            retorno.setCod(retornoModuloArr.length >= 5 ? retornoModuloArr[4] : null);
            retorno.setMensagemSEFAZ(retornoModuloArr.length >= 6 ? retornoModuloArr[5] : null);
            retorno.setArquivoBase64(retornoModuloArr.length >= 7 ? retornoModuloArr[6] : null);
            retorno.setTimestamp(retornoModuloArr.length >= 8 ? retornoModuloArr[7] : null);
            retorno.setChaveConsulta(retornoModuloArr.length >= 9 ? retornoModuloArr[8] : null);
            retorno.setValorTotalCFe(retornoModuloArr.length >= 10 ? retornoModuloArr[9] : null);
            retorno.setCPFCNPJValue(retornoModuloArr.length >= 11 ? retornoModuloArr[10] : null);
            retorno.setAssinaturaQRCODE(retornoModuloArr.length >= 12 ? retornoModuloArr[11] : null);
            retorno.setRetornoMFE(retornoModulo);
            
            if (isLogAtivado && log.getId() != null) {
                log.setDataRetorno(LocalDateTime.now());
                log.setRetornoModulo(retornoModulo);
                this.logRepository.save(log);
            }

            return retorno;

        } catch (Exception e) {
            throw new IntegracaoException();
        }
    }

    public RetornoConsultaStatusOperacional consultarStatusOperacional() {
        Config config = this.getConfig();
        IntegradorMFEDireto integrador = new IntegradorMFEDireto();
        try {
            String retornoModulo = integrador.consultarStatusOperacional(Utils.gerarNumeroSessao(), config.getCodigoAtivacao(), config.getLibsDir());
            String[] retornoModuloArr = retornoModulo.split("[|]");

            RetornoConsultaStatusOperacional retorno = new RetornoConsultaStatusOperacional();
            retorno.setRetornoMFE(retornoModulo);
            retorno.setNumeroSessao(retornoModuloArr.length >= 1 ? retornoModuloArr[0] : null);
            retorno.setEeeee(retornoModuloArr.length >= 2 ? retornoModuloArr[1] : null);
            retorno.setMensagem(retornoModuloArr.length >= 3 ? retornoModuloArr[2] : null);
            retorno.setCod(retornoModuloArr.length >= 4 ? retornoModuloArr[3] : null);
            retorno.setMensagemSEFAZ(retornoModuloArr.length >= 5 ? retornoModuloArr[4] : null);

            RetornoConsultaStatusOperacional.ConteudoRetorno conteudoRetorno = new RetornoConsultaStatusOperacional.ConteudoRetorno();
            retorno.setConteudoRetorno(conteudoRetorno);
            conteudoRetorno.setNserie(retornoModuloArr.length >= 6 ? retornoModuloArr[5] : null);
            conteudoRetorno.setTipoLan(retornoModuloArr.length >= 7 ? retornoModuloArr[6] : null);
            conteudoRetorno.setLanIP(retornoModuloArr.length >= 8 ? retornoModuloArr[7] : null);
            conteudoRetorno.setLanMac(retornoModuloArr.length >= 9 ? retornoModuloArr[8] : null);
            conteudoRetorno.setLanMasc(retornoModuloArr.length >= 10 ? retornoModuloArr[9] : null);
            conteudoRetorno.setLanGw(retornoModuloArr.length >= 11 ? retornoModuloArr[10] : null);
            conteudoRetorno.setLanDns1(retornoModuloArr.length >= 12 ? retornoModuloArr[11] : null);
            conteudoRetorno.setLanDns2(retornoModuloArr.length >= 13 ? retornoModuloArr[12] : null);
            conteudoRetorno.setStatusLan(retornoModuloArr.length >= 14 ? retornoModuloArr[13] : null);
            conteudoRetorno.setNivelBateria(retornoModuloArr.length >= 15 ? retornoModuloArr[14] : null);
            conteudoRetorno.setMtTotal(retornoModuloArr.length >= 16 ? retornoModuloArr[15] : null);
            conteudoRetorno.setMtUsada(retornoModuloArr.length >= 17 ? retornoModuloArr[16] : null);
            conteudoRetorno.setDhAtual(retornoModuloArr.length >= 18 ? retornoModuloArr[17] : null);
            conteudoRetorno.setVerSb(retornoModuloArr.length >= 19 ? retornoModuloArr[18] : null);
            conteudoRetorno.setVerLayout(retornoModuloArr.length >= 20 ? retornoModuloArr[19] : null);
            conteudoRetorno.setUltimoCFeSat(retornoModuloArr.length >= 21 ? retornoModuloArr[20] : null);
            conteudoRetorno.setListaInicial(retornoModuloArr.length >= 22 ? retornoModuloArr[21] : null);
            conteudoRetorno.setListaFinal(retornoModuloArr.length >= 23 ? retornoModuloArr[22] : null);
            conteudoRetorno.setDhCfe(retornoModuloArr.length >= 24 ? retornoModuloArr[23] : null);
            conteudoRetorno.setDhUltima(retornoModuloArr.length >= 25 ? retornoModuloArr[24] : null);
            conteudoRetorno.setCertEmissao(retornoModuloArr.length >= 26 ? retornoModuloArr[25] : null);
            conteudoRetorno.setCertVencimento(retornoModuloArr.length >= 27 ? retornoModuloArr[26] : null);
            conteudoRetorno.setEstadoOperacao(this.getEstadoOperacional(retornoModuloArr.length >= 28 ? retornoModuloArr[27] : null));
            
            return retorno;
        } catch (Exception e) {
            throw new IntegracaoException(e.getMessage());
        }
    }

    public RetornoExtrairLogsDTO extrairLogs() {
        Config config = this.getConfig();
        IntegradorMFEDireto integrador = new IntegradorMFEDireto();
        try {
            String retornoModulo = integrador.extrairLogs(Utils.gerarNumeroSessao(), config.getCodigoAtivacao(), config.getLibsDir());
            String[] retornoModuloArr = retornoModulo.split("[|]");

            RetornoExtrairLogsDTO retorno = new RetornoExtrairLogsDTO();
            retorno.setNumeroSessao(retornoModuloArr.length >= 1 ? retornoModuloArr[0] : null);
            retorno.setEeeee(retornoModuloArr.length >= 2 ? retornoModuloArr[1] : null);            
            retorno.setMensagem(retornoModuloArr.length >= 4 ? retornoModuloArr[3] : null);
            retorno.setCod(retornoModuloArr.length >= 5 ? retornoModuloArr[4] : null);
            retorno.setMensagemSEFAZ(retornoModuloArr.length >= 6 ? retornoModuloArr[5] : null);
            retorno.setArquivoBase64(retornoModuloArr.length >= 7 ? retornoModuloArr[6] : null);

            return retorno;
        } catch (UnsupportedEncodingException e) {
            throw new IntegracaoException();
        }
    }

    private String getEstadoOperacional(String codigo) {
        switch(codigo){
            case "0": return "DESBLOQUEADO";
            case "1": return "BLOQUEIO SEFAZ";
            case "2": return "BLOQUEIO CONTRIBUINTE";
            case "3": return "BLOQUEIO AUTONOMO";
            case "4": return "BLOQUEIO PARA DESATIVACAO";
            default: return "";
        }
    }

    private Config getConfig() {
        List<Config> config = this.configRepository.findAll();
        if (config.size() == 0)
            throw new ConfigNotFoundException();
        
        return config.get(0);
    }

}
