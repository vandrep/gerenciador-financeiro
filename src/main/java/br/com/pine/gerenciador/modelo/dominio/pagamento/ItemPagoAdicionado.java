package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.pagamento.AdicionaItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

public class ItemPagoAdicionado extends EventoDominio {
    public String nome;
    public int quantidade;
    public String unidadeMedida;
    public float valorUnidade;

    public ItemPagoAdicionado(){
    }

    public ItemPagoAdicionado(AdicionaItemPago umComando) {
        super(umComando.idEntidade);
        this.nome = umComando.nome;
        this.unidadeMedida = umComando.unidadeMedida;
        this.valorUnidade = umComando.valor;
        this.quantidade = umComando.quantidade;
    }
}
