package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraNomeDoRecebedor;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.time.LocalDateTime;

public class NomeDoRecebedorAlterado implements EventoDeDominioTransacao {
    private LocalDateTime ocorridoEm;
    private int versao;
    private AlteraNomeDoRecebedor comando;

    public NomeDoRecebedorAlterado() {
    }

    public NomeDoRecebedorAlterado(AlteraNomeDoRecebedor umComando) {
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
    public String nomeDoRecebedor(){
        return comando.nomeDoRecebedor;
    }
}
