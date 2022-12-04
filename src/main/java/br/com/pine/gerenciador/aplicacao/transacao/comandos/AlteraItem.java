package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AlteraItem implements Comando {
    public String idTransacao;
    public String descricaoAnterior;
    public String descricaoNova;
    public int quantidadeNova;
    public String unidadeMedidaNova;
    public float valorUnidadeNova;
}
