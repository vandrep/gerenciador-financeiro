package br.com.pine.gerenciador.modelo.dominio.conta;

import br.com.pine.gerenciador.modelo.dominio.transacao.ValorMonetario;

import java.math.BigDecimal;

public class Conta {
    private IdConta idConta;
    private String nome;
    private String tipoConta;
    private BigDecimal saldo;

    public void paga(ValorMonetario umValor){

    }

    public void recebe(ValorMonetario umValor){

    }
}
