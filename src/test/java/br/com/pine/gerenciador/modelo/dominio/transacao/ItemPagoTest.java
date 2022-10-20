package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NOME_NULO;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertDoesNotThrow(() -> ItemPago.unidade(umaDescricao, umaQuantidade, umValorMonetario));
    }

    @Test
    void criaItemPagoNomeNuloComErro() {
        umaDescricao = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> ItemPago.unidade(umaDescricao, umaQuantidade, umValorMonetario));

        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }
}