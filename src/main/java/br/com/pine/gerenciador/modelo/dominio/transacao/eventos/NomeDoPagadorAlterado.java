package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraNomeDoPagador;

import java.time.LocalDateTime;

public class NomeDoPagadorAlterado implements EventoDeDominioTransacao {
    private LocalDateTime ocorridoEm;
    private AlteraNomeDoPagador comando;

    public NomeDoPagadorAlterado() {
    }

    public NomeDoPagadorAlterado(AlteraNomeDoPagador umComando) {
        this.ocorridoEm = LocalDateTime.now();
        this.comando = umComando;
    }

    public String idTransacao() {
        return this.comando.idTransacao;
    }

    public String nomeDoPagador() {
        return this.comando.nomeDoPagador;
    }

    @Override
    public String idEntidade() {
        return idTransacao();
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
