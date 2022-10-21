package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;

import java.time.LocalDateTime;

public class ItemPagoRemovido implements EventoDeDominioTransacao {
    public LocalDateTime ocorridoEm;
    private RemoveItemPago comando;

    public ItemPagoRemovido() {
    }

    public ItemPagoRemovido(RemoveItemPago umComando) {
        this.ocorridoEm = LocalDateTime.now();
        this.comando = umComando;
    }

    public String idTransacao() {
        return comando.idTransacao;
    }

    public String descricao() {
        return comando.descricao;
    }

    public int quantidade() {
        return comando.quantidade;
    }

    public String tipoUnidadeMedida() {
        return comando.tipoUnidadeMedida;
    }

    public float valorUnidade() {
        return comando.valorUnidade;
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
