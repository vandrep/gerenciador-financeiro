package br.com.pine.gerenciador.modelo.dominio.pagamento.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdTransacao;

import java.time.LocalDateTime;
import java.util.Date;

public class TransacaoCriada extends EventoDeDominio {
    public IdTransacao idTransacao;
    public LocalDateTime ocorridoEm;
    public Date data;
    public float valor;
    public String nomeFornecedor;
    public String nomeBeneficiario;

    public TransacaoCriada() {
    }

    public TransacaoCriada(CriaTransacao umComando, String umIdEntidade) {
        this.idTransacao = new IdTransacao(umIdEntidade);
        this.ocorridoEm = LocalDateTime.now();
        this.data = umComando.data;
        this.valor = umComando.valor;
        this.nomeFornecedor = umComando.nomeDoPagador;
        this.nomeBeneficiario = umComando.nomeDoRecebedor;
    }

    @Override
    public String getIdEntidade() {
        return idTransacao.id();
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
