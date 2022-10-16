package br.com.pine.gerenciador.modelo.dominio.pagamento.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.UnidadeMedida;

public class ItemPagoRemovido extends EventoDominio {
    public String descricao;
    public int quantidade;
    public UnidadeMedida unidadeMedida;
    public float valorUnidade;

    public ItemPagoRemovido() {
    }

    public ItemPagoRemovido(RemoveItemPago umComando) {
        super(umComando.idEntidade);
        this.descricao = umComando.descricao;
        this.unidadeMedida = UnidadeMedida.valueOf(umComando.unidadeMedida);
        this.valorUnidade = umComando.valorUnidade;
        this.quantidade = umComando.quantidade;
    }
}
