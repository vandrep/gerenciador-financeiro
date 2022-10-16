package br.com.pine.gerenciador.modelo.dominio.pagamento.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.AdicionaPagamento;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdTransacao;

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
