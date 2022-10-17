package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AtualizaTransacao extends Comando {
    public String idTransacao;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;
}
