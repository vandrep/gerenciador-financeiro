package br.com.pine.gerenciador.modelo.dominio.transacao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.TIPO_UNIDADE_DE_MEDIDA_INVALIDA;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.TIPO_UNIDADE_DE_MEDIDA_MULTIPLICADOR_FORA_DO_INTERVALO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaIntervaloArgumento;

public enum TipoUnidadeMedida {
    KG {
        public BigDecimal multiplicadorDaMedida(BigDecimal umMultiplicador) {
            validaIntervaloArgumento(umMultiplicador.floatValue(), 0.001f, 1000.0f, TIPO_UNIDADE_DE_MEDIDA_MULTIPLICADOR_FORA_DO_INTERVALO);
            return new BigDecimal(umMultiplicador.toString()).setScale(3, RoundingMode.HALF_EVEN);
        }
    },
    UN {
        public BigDecimal multiplicadorDaMedida(BigDecimal umMultiplicador) {
            validaIntervaloArgumento(umMultiplicador.intValue(), 1, 1000, TIPO_UNIDADE_DE_MEDIDA_MULTIPLICADOR_FORA_DO_INTERVALO);
            return new BigDecimal(umMultiplicador.toString()).setScale(0, RoundingMode.HALF_EVEN);
        }
    };

    public BigDecimal multiplicadorDaMedida(BigDecimal umMultiplicador) {
        throw new IllegalArgumentException(TIPO_UNIDADE_DE_MEDIDA_INVALIDA.mensagem);
    }
}
