package br.com.pine.gerenciador.aplicacao.pagamento.dados;

import br.com.pine.gerenciador.modelo.dominio.pagamento.Parcela;

import java.util.Date;

public class DadosParcela {
    public Date dataPagamento;
    public float valor;
    public String estadoPagamento;

    public DadosParcela(Parcela umaParcela) {
        this.dataPagamento = umaParcela.dataPagamento();
        this.valor = umaParcela.valor();
        this.estadoPagamento = umaParcela.estadoPagamento();
    }
}
