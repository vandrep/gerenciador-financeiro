package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.transacao.AdicionaItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

public class ItemPagoAdicionado extends EventoDominio {
    public String descricao;
    public int quantidade;
    public UnidadeMedida unidadeMedida;
    public float valorUnidade;

    public ItemPagoAdicionado(){
    }

    public ItemPagoAdicionado(AdicionaItemPago umComando) {
        super(umComando.idEntidade);
        this.descricao = umComando.descricao;
        this.unidadeMedida = UnidadeMedida.valueOf(umComando.unidadeMedida);
        this.valorUnidade = umComando.valorUnidade;
        this.quantidade = umComando.quantidade;
    }
}
