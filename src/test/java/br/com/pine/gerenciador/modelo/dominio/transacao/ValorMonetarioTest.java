package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Currency;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.VALOR_MONETARIO_VALOR_MENOR_QUE_ZERO;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class ValorMonetarioTest {

    @Inject
    Fixtures fixtures;
    Currency umaMoeda;
    float umValorPositivo;
    float umValorNegativo;
    float umValorComQuantidadeCasasDecimaisErrado;

    @BeforeEach
    void setUp() {
        umaMoeda = fixtures.moedaReal();
        umValorPositivo = fixtures.valorPositivo();
        umValorNegativo = fixtures.valorNegativo();
        umValorComQuantidadeCasasDecimaisErrado = fixtures.valorComCasasDecimaisAMais(umaMoeda);
    }

    @Test
    void criaValorMonetarioValidoComSucesso() {
        assertDoesNotThrow(() -> ValorMonetario.emReal(umValorPositivo));
    }

    @Test
    void criaValorMonetarioComValorNegativoComErro() {
        var erro = assertThrows(
                IllegalArgumentException.class,
                () -> ValorMonetario.emReal(umValorNegativo));
        assertEquals(VALOR_MONETARIO_VALOR_MENOR_QUE_ZERO.mensagem, erro.getMessage());
    }
}