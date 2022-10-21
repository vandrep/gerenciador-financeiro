package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.time.LocalDateTime;

public class TransacaoCriada implements EventoDeDominioTransacao {
    private LocalDateTime ocorridoEm;
    private int versao;
    private String idTransacao;
    private CriaTransacao comando;

    public TransacaoCriada() {
    }

    public TransacaoCriada(CriaTransacao umComando,
                           String umIdTransacao) {
        ocorridoEm = LocalDateTime.now();
        versao = 1;
        idTransacao = umIdTransacao;
        comando = umComando;
    }

    @Override
    public String idEntidade() {
        return idTransacao;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int getVersao() {
        return versao;
    }

    public String getIdTransacao(){
        return idEntidade();
    }

    @Override
    public Comando getComando() {
        return comando;
    }

    public String descricao(){
        return comando.descricao;
    }
    public float valor(){
        return comando.valor;
    }
    public String nomeDoPagador(){
        return comando.nomeDoPagador;
    }
    public String nomeDoRecebedor(){
        return comando.nomeDoRecebedor;
    }
    public String idPagamento(){
        return comando.idPagamento;
    }
}
