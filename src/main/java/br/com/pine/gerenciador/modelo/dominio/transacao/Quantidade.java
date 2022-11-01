package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.math.BigDecimal;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.QUANTIDADE_MULTIPLICADOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.QUANTIDADE_TIPO_UNIDADE_DE_MEDIDA_NULO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;

public class Quantidade implements ObjetoDeValor {
    private BigDecimal multiplicador;
    private TipoUnidadeMedida tipoUnidadeMedida;

    public Quantidade(BigDecimal umMultiplicador,
                      TipoUnidadeMedida tipoUnidadeMedida) {
        this.setTipoUnidadeMedida(tipoUnidadeMedida);
        this.setMultiplicador(umMultiplicador);
    }

    public BigDecimal multiplicador() {
        return multiplicador;
    }

    public TipoUnidadeMedida tipoUnidadeDeMedida() {
        return tipoUnidadeMedida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantidade that = (Quantidade) o;

        if (!multiplicador.equals(that.multiplicador)) return false;
        return tipoUnidadeMedida == that.tipoUnidadeMedida;
    }

    @Override
    public int hashCode() {
        int result = multiplicador.hashCode();
        result = 31 * result + tipoUnidadeMedida.hashCode();
        return result;
    }

    private void setTipoUnidadeMedida(TipoUnidadeMedida umTipoUnidadeMedida) {
        validaArgumentoNaoNulo(umTipoUnidadeMedida, QUANTIDADE_TIPO_UNIDADE_DE_MEDIDA_NULO);
        this.tipoUnidadeMedida = umTipoUnidadeMedida;
    }

    private void setMultiplicador(BigDecimal umMultiplicador) {
        validaArgumentoNaoNulo(umMultiplicador, QUANTIDADE_MULTIPLICADOR_NULO);
        this.multiplicador = this.tipoUnidadeMedida.multiplicadorDaMedida(umMultiplicador);
    }
}
