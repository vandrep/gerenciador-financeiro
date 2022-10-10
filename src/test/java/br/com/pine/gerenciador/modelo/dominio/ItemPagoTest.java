package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.pagamento.ItemPago;
import br.com.pine.gerenciador.modelo.dominio.pagamento.ValorMonetario;
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

    String umNome;
    ValorMonetario umValorMonetario;
    int umaQuantidade;

    @BeforeEach
    void setUp() {
        umNome = fixtures.umaStringAleatoria(10);
        umValorMonetario = fixtures.umValorMonetarioRealPositivo();
        umaQuantidade = fixtures.umInteiroAleatorio(1, 10);
    }

    @Test
    void criaItemPagoComSucesso(){
        assertDoesNotThrow(() -> new ItemPago(umNome, umValorMonetario, umaQuantidade));
    }

    @Test
    void criaItemPagoNomeNuloComErro(){
        umNome = null;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umNome, umValorMonetario, umaQuantidade));
        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoNomeVazioComErro(){
        umNome = "";
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umNome, umValorMonetario, umaQuantidade));
        assertEquals(ITEM_PAGO_NOME_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoValorMonetarioVazioComErro(){
        umValorMonetario = null;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umNome, umValorMonetario, umaQuantidade));
        assertEquals(ITEM_PAGO_VALOR_MONETARIO_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void criaItemPagoQuantidadeMenorQueUmComErro(){
        umaQuantidade = 0;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new ItemPago(umNome, umValorMonetario, umaQuantidade));
        assertEquals(ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem, erro.getMessage());
    }
}