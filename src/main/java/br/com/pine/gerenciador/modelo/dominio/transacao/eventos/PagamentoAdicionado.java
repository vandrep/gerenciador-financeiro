package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.transacao.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.IdTransacao;

import java.time.LocalDateTime;

public class PagamentoAdicionado extends EventoDeDominio {
    public IdTransacao idTransacao;
    public LocalDateTime ocorridoEm;
    public IdPagamento idPagamento;

    public PagamentoAdicionado() {
    }

    public PagamentoAdicionado(AdicionaPagamento umComando) {
        this.idTransacao = new IdTransacao(umComando.idTransacao);
        this.ocorridoEm = LocalDateTime.now();
        this.idPagamento = new IdPagamento(umComando.idPagamento);
    }

    @Override
    public String getIdEntidade() {
        return idTransacao.id();
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
