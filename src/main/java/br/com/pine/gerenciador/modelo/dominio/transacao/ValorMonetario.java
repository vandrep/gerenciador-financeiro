package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.VALOR_MONETARIO_VALOR_MENOR_QUE_ZERO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoMaiorOuIgualA;

public class ValorMonetario implements ObjetoDeValor {
    private Currency moeda;
    private BigDecimal valor;

    public static ValorMonetario emReal(float umValor) {
        var real = Currency.getInstance("BRL");
        var valorFormatado =
                new BigDecimal(umValor).setScale(real.getDefaultFractionDigits(), RoundingMode.HALF_EVEN);
        return new ValorMonetario(real, valorFormatado);
    }

    public Currency moeda() {
        return this.moeda;
    }

    public BigDecimal valor() {
        return this.valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValorMonetario that = (ValorMonetario) o;

        if (!moeda.equals(that.moeda)) return false;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        int result = moeda.hashCode();
        result = 31 * result + valor.hashCode();
        return result;
    }

    public ValorMonetario(Currency umaMoeda,
                           BigDecimal umValor) {
        this.setMoeda(umaMoeda);
        this.setValor(umValor);
    }

    private void setMoeda(Currency umaMoeda) {
        this.moeda = umaMoeda;
    }

    private void setValor(BigDecimal umValor) {
        validaArgumentoMaiorOuIgualA(umValor.floatValue(), 0, VALOR_MONETARIO_VALOR_MENOR_QUE_ZERO);
        this.valor = umValor;
    }

    private boolean validaQuantidadeCasasDecimais(float umValor) {
        return quantidadeCasasDecimais(umValor) <= moeda.getDefaultFractionDigits();
    }

    private int quantidadeCasasDecimais(float umValor) {
        return List.of(String.valueOf(umValor).split("\\.")).get(1).length();
    }
}
