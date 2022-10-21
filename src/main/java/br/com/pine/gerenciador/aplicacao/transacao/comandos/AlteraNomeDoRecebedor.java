package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AlteraNomeDoRecebedor implements Comando {
    public String idTransacao;
    public String nomeDoRecebedor;
}
