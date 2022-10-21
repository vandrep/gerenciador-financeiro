package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;

import java.time.LocalDateTime;

public class ItemPagoAdicionado implements EventoDeDominioTransacao {

    private LocalDateTime ocorridoEm;
    private AdicionaItemPago comando;

    public ItemPagoAdicionado() {
    }

    public ItemPagoAdicionado(AdicionaItemPago comando) {
        this.ocorridoEm = LocalDateTime.now();
        this.comando = comando;
    }

    public String idTransacao(){
        return comando.idTransacao;
    }
    public String descricao(){
        return comando.descricao;
    }
    public int quantidade(){
        return comando.quantidade;
    }
    public String tipoUnidadeMedida(){
        return comando.tipoUnidadeMedida;
    }
    public float valorUnidade(){
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
