package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.Entidade;
import br.com.pine.gerenciador.modelo.dominio.conta.IdConta;
import br.com.pine.gerenciador.modelo.dominio.transacao.ValorMonetario;

import java.util.Date;

public class Parcela implements Entidade {
    private IdParcela idParcela;
    private Date dataPagamento;
    private ValorMonetario valor;
    private EstadoPagamento estadoPagamento;
    private IdPagamento idPagamento;
    private IdConta idConta;

    public Parcela(Date umadataPagamento,
                   ValorMonetario umValor,
                   String umEstadoPagamento,
                   IdPagamento umIdPagamento,
                   IdConta umIdConta) {
        idParcela = new IdParcela();
        dataPagamento = umadataPagamento;
        valor = umValor;
        setEstadoPagamento(umEstadoPagamento);
        idPagamento = umIdPagamento;
        idConta = umIdConta;
    }

    private void setEstadoPagamento(String umEstadoPagamento) {
//        valida a String
        estadoPagamento = EstadoPagamento.valueOf(umEstadoPagamento);
    }

    public IdParcela idParcela(){
        return idParcela;
    }

    public Date dataPagamento() {
        return dataPagamento;
    }

    public float valor() {
        return valor.valor().floatValue();
    }

    public String estadoPagamento() {
        return estadoPagamento.name();
    }
}
