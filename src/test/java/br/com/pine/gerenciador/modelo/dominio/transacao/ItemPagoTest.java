//package br.com.pine.gerenciador.modelo.dominio.transacao;
//
//import br.com.pine.Fixtures;
//import io.quarkus.test.junit.QuarkusTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.inject.Inject;
//
//import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ITEM_PAGO_NOME_NULO;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@QuarkusTest
//class ItemPagoTest {
//    @Inject
//    Fixtures fixtures;
//    String umaDescricao;
//    int umaQuantidade;
//    TipoUnidadeMedida umaTipoUnidadeMedida;
//    ValorMonetario umValorMonetario;
//
//    @BeforeEach
//    void setUp() {
//        umaDescricao = fixtures.umaStringAleatoria(10);
//        umValorItem = fixtures.umValorItem();
//        categorias = fixtures.categorias();
//    }
//
//    @Test
//    void criaItemPagoComSucesso() {
//        assertDoesNotThrow(() -> new ItemPago(umaDescricao, umaQuantidade, "UN", umValorMonetario));
//    }
//
//    @Test
//    void criaItemPagoNomeNuloComErro() {
//        umaDescricao = null;
//
//        var erro = assertThrows(IllegalArgumentException.class,
//                () -> ItemPago.criaItemPago(umaDescricao, umaQuantidade, "UN", umValorMonetario));
//
//        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
//    }
//}