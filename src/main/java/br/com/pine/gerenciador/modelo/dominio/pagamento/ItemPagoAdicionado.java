package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.pagamento.AdicionaItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

public class ItemPagoAdicionado extends EventoDominio {
    public String nome;
    public float valor;
    public int quantidade;

    public ItemPagoAdicionado(){}

    public ItemPagoAdicionado(AdicionaItemPago umComando) {
        super();
        this.nome = umComando.nome;
        this.valor = umComando.valor;
        this.quantidade = umComando.quantidade;
    }
}
