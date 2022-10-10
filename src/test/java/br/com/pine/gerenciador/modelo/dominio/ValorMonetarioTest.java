package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.pagamento.ValorMonetario;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Currency;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;
import static org.junit.jupiter.api.Assertions.*;

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
        assertDoesNotThrow(() -> new ValorMonetario(umaMoeda, umValorPositivo));
    }

    @Test
    void criaValorMonetarioSemMoedaComErro() {
        var erro = assertThrows(
                IllegalArgumentException.class,
                () -> new ValorMonetario(null, umValorPositivo));
        assertEquals(MOEDA_NULA.mensagem, erro.getMessage());
    }

    @Test
    void criaValorMonetarioComValorNegativoComErro() {
        var erro = assertThrows(
                IllegalArgumentException.class,
                () -> new ValorMonetario(umaMoeda, umValorNegativo));
        assertEquals(MOEDA_MENOR_QUE_ZERO.mensagem, erro.getMessage());
    }

    @Test
    void criaValorMonetarioComValorComCasasDecimaisIncorretoComErro() {
        var erro = assertThrows(
                IllegalArgumentException.class,
                () -> new ValorMonetario(umaMoeda, umValorComQuantidadeCasasDecimaisErrado));
        assertEquals(MOEDA_QUANTIDADE_CASAS_DECIMAIS_INVALIDA.mensagem, erro.getMessage());
    }
}