package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.transacao.IdTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.UnidadeMedida;

import java.time.LocalDateTime;

public class ItemPagoRemovido extends EventoDeDominio {
    public IdTransacao idTransacao;
    public LocalDateTime ocorridoEm;
    public String descricao;
    public int quantidade;
    public UnidadeMedida unidadeMedida;
    public float valorUnidade;

    public ItemPagoRemovido() {
    }

    public ItemPagoRemovido(RemoveItemPago umComando) {
        this.idTransacao = new IdTransacao(umComando.idTransacao);
        this.ocorridoEm = LocalDateTime.now();
        this.descricao = umComando.descricao;
        this.unidadeMedida = UnidadeMedida.valueOf(umComando.unidadeMedida);
        this.valorUnidade = umComando.valorUnidade;
        this.quantidade = umComando.quantidade;
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
