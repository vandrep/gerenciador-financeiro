package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.pagamento.CriaPagamentoEmReal;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

import java.util.Date;

public class PagamentoEmRealCriado extends EventoDominio {
    public Date data;
    public float valor;
    public String nomeFornecedor;
    public String nomeBeneficiario;

    public PagamentoEmRealCriado() {
    }

    public PagamentoEmRealCriado(CriaPagamentoEmReal umComando) {
        super();
        this.data = umComando.data;
        this.valor = umComando.valor;
        this.nomeFornecedor = umComando.nomeFornecedor;
        this.nomeBeneficiario = umComando.nomeBeneficiario;
    }
}
