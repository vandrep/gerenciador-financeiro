package br.com.pine.gerenciador.aplicacao.transacao.dados;

import br.com.pine.gerenciador.modelo.dominio.transacao.ItemPago;

public class DadosItemPago {
    public String descricao;
    public float quantidade;
    public String unidadeDeMedida;
    public float valorDaUnidade;

    public DadosItemPago(ItemPago umItemPago){
        this.descricao = umItemPago.descricao();
        this.quantidade = umItemPago.multiplicador().floatValue();
        this.unidadeDeMedida = umItemPago.tipoUnidadeMedida().name();
        this.valorDaUnidade = umItemPago.valorUnidade();
    }
}
