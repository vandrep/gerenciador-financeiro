package br.com.pine.gerenciador.aplicacao.transacao.dados;

import br.com.pine.gerenciador.modelo.dominio.transacao.ItemPago;

public class DadosItemPago {
    public String descricao;
    public int quantidade;
    public String unidadeDeMedida;
    public float valorDaUnidade;

    public DadosItemPago(ItemPago umItemPago){
        this.descricao = umItemPago.getDescricao();
        this.quantidade = umItemPago.getQuantidade();
        this.unidadeDeMedida = umItemPago.getUnidadeMedida().name();
        this.valorDaUnidade = umItemPago.getValorUnidade();
    }
}
