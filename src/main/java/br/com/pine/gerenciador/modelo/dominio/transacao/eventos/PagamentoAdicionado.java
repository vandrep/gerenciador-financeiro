package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;

import java.time.LocalDateTime;

public class PagamentoAdicionado extends EventoDeDominio {
    public String idEntidade;
    public LocalDateTime ocorridoEm;
    public String idPagamento;

    public PagamentoAdicionado() {
    }

    public PagamentoAdicionado(AdicionaPagamento umComando) {
        this.idEntidade = umComando.idTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.idPagamento = umComando.idPagamento;
    }

    @Override
    public String getIdTransacao() {
        return idEntidade;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
