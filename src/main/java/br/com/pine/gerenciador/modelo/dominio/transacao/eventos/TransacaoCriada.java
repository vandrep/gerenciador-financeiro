package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;

import java.time.LocalDateTime;

public class TransacaoCriada extends EventoDeDominio {
    public String idTransacao;
    public LocalDateTime ocorridoEm;
    public String idPagamento;
    public String descricao;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;

    public TransacaoCriada() {
    }

    public TransacaoCriada(CriaTransacao umComando,
                           String umIdTransacao) {
        this.idTransacao = umIdTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.descricao = umComando.descricao;
        this.valor = umComando.valor;
        this.nomeDoPagador = umComando.nomeDoPagador;
        this.nomeDoRecebedor = umComando.nomeDoRecebedor;
        this.idPagamento = umComando.idPagamento;
    }

    @Override
    public String getIdTransacao() {
        return idTransacao;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
