package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AlteraItemPago extends Comando {
    public String idTransacao;
    public String descricaoAnterior;
    public int quantidadeAnterior;
    public String unidadeMedidaAnterior;
    public float valorUnidadeAnterior;
    public String descricaoNova;
    public int quantidadeNova;
    public String unidadeMedidaNova;
    public float valorUnidadeNova;
}
