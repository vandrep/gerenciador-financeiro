package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;

import java.time.LocalDateTime;

public class TransacaoCriada implements EventoDeDominioTransacao {
    public LocalDateTime ocorridoEm;
    public String idTransacao;
    public String descricao;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;
    public String idPagamento;

    public TransacaoCriada() {
    }

    public TransacaoCriada(CriaTransacao umComando,
                           String umIdTransacao) {
        this.ocorridoEm = LocalDateTime.now();
        this.idTransacao = umIdTransacao;
        this.descricao = umComando.descricao;
        this.valor = umComando.valor;
        this.nomeDoPagador = umComando.nomeDoPagador;
        this.nomeDoRecebedor = umComando.nomeDoRecebedor;
        this.idPagamento = umComando.idPagamento;
    }

    @Override
    public String idEntidade() {
        return idTransacao;
    }

    @Override
    public LocalDateTime ocorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int versaoDoEvento() {
        return 1;
    }
}
