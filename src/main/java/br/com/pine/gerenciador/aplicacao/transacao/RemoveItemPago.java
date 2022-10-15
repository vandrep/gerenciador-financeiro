package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class RemoveItemPago extends Comando {
    public String descricao;
    public int quantidade;
    public String unidadeMedida;
    public float valorUnidade;
}
