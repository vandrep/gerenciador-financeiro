package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;

import java.time.LocalDateTime;

public class TransacaoCriada extends EventoDeDominio {
    public String idEntidade;
    public LocalDateTime ocorridoEm;
    public float valor;
    public String nomeFornecedor;
    public String nomeBeneficiario;

    public TransacaoCriada() {
    }

    public TransacaoCriada(CriaTransacao umComando, String umIdTransacao) {
        this.idEntidade = umIdTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.valor = umComando.valor;
        this.nomeFornecedor = umComando.nomeDoPagador;
        this.nomeBeneficiario = umComando.nomeDoRecebedor;
    }

    @Override
    public String getIdEntidade() {
        return idEntidade;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
