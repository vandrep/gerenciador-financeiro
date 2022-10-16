package br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AlteraItemPago extends Comando {
    public String descricaoAnterior;
    public int quantidadeAnterior;
    public String unidadeMedidaAnterior;
    public float valorUnidadeAnterior;
    public String descricaoNova;
    public int quantidadeNova;
    public String unidadeMedidaNova;
    public float valorUnidadeNova;
}
