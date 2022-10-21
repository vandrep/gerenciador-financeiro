package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.time.LocalDateTime;

public class ItemPagoAlterado implements EventoDeDominioTransacao {
    private LocalDateTime ocorridoEm;
    private int versao;
    private AlteraItemPago comando;

    public ItemPagoAlterado() {
    }

    public ItemPagoAlterado(AlteraItemPago umComando) {
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
        return this.versao;
    }

    @Override
    public Comando getComando() {
        return comando;
    }

    public String descricaoAnterior() {
        return comando.descricaoAnterior;
    }

    public int quantidadeAnterior() {
        return comando.quantidadeAnterior;
    }

    public String unidadeMedidaAnterior() {
        return comando.unidadeMedidaAnterior;
    }

    public float valorUnidadeAnterior() {
        return comando.valorUnidadeAnterior;
    }

    public String descricaoNova() {
        return comando.descricaoNova;
    }

    public int quantidadeNova() {
        return comando.quantidadeNova;
    }

    public String unidadeMedidaNova() {
        return comando.unidadeMedidaNova;
    }

    public float valorUnidadeNova() {
        return comando.valorUnidadeNova;
    }
}
