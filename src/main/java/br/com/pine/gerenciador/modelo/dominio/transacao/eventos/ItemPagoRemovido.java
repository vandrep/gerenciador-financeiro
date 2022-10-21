package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;

import java.time.LocalDateTime;

public class ItemPagoRemovido implements EventoDeDominioTransacao {
    public LocalDateTime ocorridoEm;
    private int versao;
    private RemoveItemPago comando;

    public ItemPagoRemovido() {
    }

    public ItemPagoRemovido(RemoveItemPago umComando) {
        ocorridoEm = LocalDateTime.now();
        versao = 1;
        comando = umComando;
    }

    @Override
    public String idEntidade() {
        return comando.idTransacao;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int getVersao() {
        return versao;
    }

    @Override
    public RemoveItemPago getComando(){
        return comando;
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
}
