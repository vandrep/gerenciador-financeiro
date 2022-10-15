package br.com.pine.gerenciador.aplicacao.pagamento;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AdicionaItemPago extends Comando {
    public String idPagamento;
    public String nome;
    public int quantidade;
    public String unidadeMedida;
    public float valor;
}
