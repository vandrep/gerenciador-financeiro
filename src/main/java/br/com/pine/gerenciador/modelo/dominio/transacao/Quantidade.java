package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.math.BigDecimal;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.QUANTIDADE_MULTIPLICADOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.QUANTIDADE_TIPO_UNIDADE_DE_MEDIDA_NULO;

public class Quantidade extends ObjetoDeValor {
    private BigDecimal multiplicador;
    private TipoUnidadeMedida tipoUnidadeMedida;

    public static Quantidade unidade(int umMultiplicador) {
        return new Quantidade(new BigDecimal(umMultiplicador), TipoUnidadeMedida.UN);
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

    private Quantidade(BigDecimal multiplicador,
                       TipoUnidadeMedida tipoUnidadeMedida) {
        this.setTipoUnidadeMedida(tipoUnidadeMedida);
        this.setMultiplicador(multiplicador);
    }

    private void setTipoUnidadeMedida(TipoUnidadeMedida umTipoUnidadeMedida) {
        validaArgumentoNaoNulo(umTipoUnidadeMedida, QUANTIDADE_TIPO_UNIDADE_DE_MEDIDA_NULO.mensagem);
        this.tipoUnidadeMedida = umTipoUnidadeMedida;
    }

    private void setMultiplicador(BigDecimal umMultiplicador) {
        validaArgumentoNaoNulo(umMultiplicador, QUANTIDADE_MULTIPLICADOR_NULO.mensagem);
        this.multiplicador = this.tipoUnidadeMedida.multiplicadorDaMedida(umMultiplicador);
    }
}
