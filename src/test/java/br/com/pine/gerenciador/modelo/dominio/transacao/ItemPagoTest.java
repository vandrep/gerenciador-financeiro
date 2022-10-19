package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
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
    TipoUnidadeMedida umaTipoUnidadeMedida;
    ValorMonetario umValorMonetario;

    @BeforeEach
    void setUp() {
        umaDescricao = fixtures.umaStringAleatoria(10);
        umaQuantidade = fixtures.umInteiroAleatorio(1, 10);
        umaTipoUnidadeMedida = TipoUnidadeMedida.UN;
        umValorMonetario = ValorMonetario.emReal(fixtures.valorPositivo());
    }

    @Test
    void criaItemPagoComSucesso() {
        assertDoesNotThrow(() -> new ItemPago(umaDescricao, umaQuantidade, umaTipoUnidadeMedida, umValorMonetario));
    }

    @Test
    void criaItemPagoNomeNuloComErro() {
        umaDescricao = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaTipoUnidadeMedida, umValorMonetario));

        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoQuantidadeMenorQueUmComErro() {
        umaQuantidade = 0;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaTipoUnidadeMedida, umValorMonetario));

        assertEquals(ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoUnidadeMedidaNulaComErro() {
        umaTipoUnidadeMedida = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umaDescricao, umaQuantidade, umaTipoUnidadeMedida, umValorMonetario));

        assertEquals(ITEM_PAGO_UNIDADE_MEDIDA_NULA.mensagem, erro.getMessage());
    }
}