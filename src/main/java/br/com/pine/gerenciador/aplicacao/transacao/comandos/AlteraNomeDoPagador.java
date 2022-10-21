package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

public class AlteraNomeDoPagador implements Comando {
    public String idTransacao;
    public String nomeDoPagador;
}
