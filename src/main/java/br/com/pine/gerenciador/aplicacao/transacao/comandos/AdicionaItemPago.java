package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AdicionaItemPago extends Comando {
    public String idTransacao;
    public String descricao;
    public int quantidade;
    public String unidadeMedida;
    public float valorUnidade;
}
