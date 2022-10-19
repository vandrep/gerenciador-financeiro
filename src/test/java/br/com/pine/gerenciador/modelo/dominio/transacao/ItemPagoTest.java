package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.transacao.ItemPago;
import br.com.pine.gerenciador.modelo.dominio.transacao.UnidadeMedida;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ItemPagoTest {
    @Inject
    Fixtures fixtures;

    String umaDescricao;
    int umaQuantidade;
    UnidadeMedida umaUnidadeMedida;
    float umValor;

    @BeforeEach
    void setUp() {
        umaDescricao = fixtures.umaStringAleatoria(10);
        umaQuantidade = fixtures.umInteiroAleatorio(1, 10);
        umaUnidadeMedida = UnidadeMedida.UNIDADE;
        umValor = fixtures.valorPositivo();
    }

    @Test
    void criaItemPagoComSucesso() {
        assertDoesNotThrow(() -> new ItemPago(umaDescricao, umaQuantidade, umaUnidadeMedida, umValor));
    }

    @Test
    void criaItemPagoNomeNuloComErro() {
        umaDescricao = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaUnidadeMedida, umValor));

        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoValorNegativoComErro() {
        umValor = fixtures.valorNegativo();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaUnidadeMedida, umValor));

        assertEquals(ITEM_PAGO_VALOR_NEGATIVO.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoQuantidadeMenorQueUmComErro() {
        umaQuantidade = 0;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaUnidadeMedida, umValor));

        assertEquals(ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoUnidadeMedidaNulaComErro() {
        umaUnidadeMedida = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaUnidadeMedida, umValor));

        assertEquals(ITEM_PAGO_UNIDADE_MEDIDA_NULA.mensagem, erro.getMessage());
    }
}