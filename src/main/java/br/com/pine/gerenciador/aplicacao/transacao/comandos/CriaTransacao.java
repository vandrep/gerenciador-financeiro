package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Date;

public class CriaTransacao extends Comando {
    public Date data;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;
}
