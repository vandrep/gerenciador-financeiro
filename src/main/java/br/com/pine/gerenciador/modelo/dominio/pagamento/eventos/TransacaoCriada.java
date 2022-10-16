package br.com.pine.gerenciador.modelo.dominio.pagamento.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

import java.util.Date;

public class TransacaoCriada extends EventoDominio {
    public Date data;
    public float valor;
    public String nomeFornecedor;
    public String nomeBeneficiario;

    public TransacaoCriada() {
    }

    public TransacaoCriada(CriaTransacao umComando) {
        super(umComando.idEntidade);
        this.data = umComando.data;
        this.valor = umComando.valor;
        this.nomeFornecedor = umComando.nomeFornecedor;
        this.nomeBeneficiario = umComando.nomeBeneficiario;
    }
}
