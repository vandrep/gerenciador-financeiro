package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;

import java.time.LocalDateTime;

public class NomeDoRecebedorAlterado implements EventoDeDominioTransacao {
    public LocalDateTime ocorridoEm;
    public String idTransacao;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;

    public NomeDoRecebedorAlterado() {
    }

    public NomeDoRecebedorAlterado(CriaTransacao umComando, String umIdTransacao) {
        this.idTransacao = umIdTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.valor = umComando.valor;
        this.nomeDoPagador = umComando.nomeDoPagador;
        this.nomeDoRecebedor = umComando.nomeDoRecebedor;
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
