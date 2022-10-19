package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;

import java.time.LocalDateTime;

public class ItemPagoAlterado extends EventoDeDominio {
    public String idTransacao;
    public LocalDateTime ocorridoEm;
    public String descricaoAnterior;
    public int quantidadeAnterior;
    public String unidadeMedidaAnterior;
    public float valorUnidadeAnterior;
    public String descricaoNova;
    public int quantidadeNova;
    public String unidadeMedidaNova;
    public float valorUnidadeNova;

    public ItemPagoAlterado() {
    }

    public ItemPagoAlterado(AlteraItemPago umComando) {
        this.idTransacao = umComando.idTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.descricaoAnterior = umComando.descricaoAnterior;
        this.quantidadeAnterior = umComando.quantidadeAnterior;
        this.unidadeMedidaAnterior = umComando.unidadeMedidaAnterior;
        this.valorUnidadeAnterior = umComando.valorUnidadeAnterior;
        this.descricaoNova = umComando.descricaoNova;
        this.quantidadeNova = umComando.quantidadeNova;
        this.unidadeMedidaNova = umComando.unidadeMedidaNova;
        this.valorUnidadeNova = umComando.valorUnidadeNova;
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
