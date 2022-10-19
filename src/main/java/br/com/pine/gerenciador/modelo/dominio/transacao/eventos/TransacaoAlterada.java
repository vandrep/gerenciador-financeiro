package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;

import java.time.LocalDateTime;

public class TransacaoAlterada extends EventoDeDominio {
    public String idEntidade;
    public LocalDateTime ocorridoEm;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;

    public TransacaoAlterada() {
    }

    public TransacaoAlterada(CriaTransacao umComando, String umIdTransacao) {
        this.idEntidade = umIdTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.valor = umComando.valor;
        this.nomeDoPagador = umComando.nomeDoPagador;
        this.nomeDoRecebedor = umComando.nomeDoRecebedor;
    }

    @Override
    public String getIdTransacao() {
        return idEntidade;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
