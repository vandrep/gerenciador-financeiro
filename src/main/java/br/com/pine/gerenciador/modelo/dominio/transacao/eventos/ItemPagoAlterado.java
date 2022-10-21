package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;

import java.time.LocalDateTime;

public class ItemPagoAlterado implements EventoDeDominioTransacao {
    public LocalDateTime ocorridoEm;
    private AlteraItemPago comando;

    public ItemPagoAlterado() {
    }

    public ItemPagoAlterado(AlteraItemPago umComando) {
        this.ocorridoEm = LocalDateTime.now();
        this.comando = umComando;
    }

    public String idTransacao() {
        return this.comando.idTransacao;
    }

    public String descricaoAnterior() {
        return this.comando.descricaoAnterior;
    }

    public int quantidadeAnterior() {
        return this.comando.quantidadeAnterior;
    }

    public String unidadeMedidaAnterior() {
        return this.comando.unidadeMedidaAnterior;
    }

    public float valorUnidadeAnterior() {
        return this.comando.valorUnidadeAnterior;
    }

    public String descricaoNova() {
        return this.comando.descricaoNova;
    }

    public int quantidadeNova() {
        return this.comando.quantidadeNova;
    }

    public String unidadeMedidaNova() {
        return this.comando.unidadeMedidaNova;
    }

    public float valorUnidadeNova() {
        return this.comando.valorUnidadeNova;
    }

    @Override
    public String idEntidade() {
        return idTransacao();
    }

    @Override
    public LocalDateTime ocorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int versaoDoEvento() {
        return 1;
    }
}
