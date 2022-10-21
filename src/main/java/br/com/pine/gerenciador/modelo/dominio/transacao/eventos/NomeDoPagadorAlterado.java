package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraNomeDoPagador;
import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.time.LocalDateTime;

public class NomeDoPagadorAlterado implements EventoDeDominioTransacao {
    private LocalDateTime ocorridoEm;
    private int versao;
    private AlteraNomeDoPagador comando;

    public NomeDoPagadorAlterado() {
    }

    public NomeDoPagadorAlterado(AlteraNomeDoPagador umComando) {
        ocorridoEm = LocalDateTime.now();
        versao = 1;
        comando = umComando;
    }

    @Override
    public String idEntidade() {
        return comando.idTransacao;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int getVersao() {
        return versao;
    }

    @Override
    public Comando getComando() {
        return comando;
    }

    public String nomeDoPagador() {
        return this.comando.nomeDoPagador;
    }
}
