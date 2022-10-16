package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.util.Currency;
import java.util.List;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.MOEDA_MENOR_QUE_ZERO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.MOEDA_NULA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.MOEDA_QUANTIDADE_CASAS_DECIMAIS_INVALIDA;

public class ValorMonetario extends ObjetoDeValor {
    private Currency moeda;
    private float valor;

    public ValorMonetario(Currency umaMoeda, float umValor) {
        this.setMoeda(umaMoeda);
        this.setValor(umValor);
    }

    public static ValorMonetario emReal(float umValor) {
        return new ValorMonetario(Currency.getInstance("BRL"), umValor);
    }

    public Currency getMoeda() {
        return this.moeda;
    }

    public float getValor() {
        return this.valor;
    }

    private void setMoeda(Currency umaMoeda) {
        validaArgumentoNaoNulo(umaMoeda, MOEDA_NULA.mensagem);
        this.moeda = umaMoeda;
    }

    private void setValor(float umValor) {
        validaArgumentoMaiorOuIgualA(umValor, 0, MOEDA_MENOR_QUE_ZERO.mensagem);
        validaArgumentoVerdadeiro(validaQuantidadeCasasDecimais(umValor), MOEDA_QUANTIDADE_CASAS_DECIMAIS_INVALIDA.mensagem);
        this.valor = umValor;
    }

    private boolean validaQuantidadeCasasDecimais(float umValor) {
        return quantidadeCasasDecimais(umValor) <= moeda.getDefaultFractionDigits();
    }

    private int quantidadeCasasDecimais(float umValor) {
        return List.of(String.valueOf(umValor).split("\\.")).get(1).length();
    }
}
