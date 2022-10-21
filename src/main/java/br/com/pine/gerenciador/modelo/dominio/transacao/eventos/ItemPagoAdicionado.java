package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.time.LocalDateTime;

public class ItemPagoAdicionado implements EventoDeDominioTransacao {

    private LocalDateTime ocorridoEm;
    private int versao;
    private AdicionaItemPago comando;

    public ItemPagoAdicionado() {
    }

    public ItemPagoAdicionado(AdicionaItemPago umComando) {
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
    public Comando getComando() {
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
