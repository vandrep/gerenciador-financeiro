package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Date;

public class AgendaPagamentoParcela extends Comando {
    public String idTransacao;
    public int numeroParcela;
    public Date dataAgendamento;
}
