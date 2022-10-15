package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.transacao.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

public class ItemPagoRemovido extends EventoDominio {
    public String descricao;
    public int quantidade;
    public String unidadeMedida;
    public float valorUnidade;

    public ItemPagoRemovido() {
    }

    public ItemPagoRemovido(RemoveItemPago umComando) {
        super(umComando.idEntidade);
        this.descricao = umComando.descricao;
        this.unidadeMedida = umComando.unidadeMedida;
        this.valorUnidade = umComando.valorUnidade;
        this.quantidade = umComando.quantidade;
    }
}
