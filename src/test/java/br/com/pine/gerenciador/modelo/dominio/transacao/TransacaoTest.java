package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.TRANSACAO_PAGAMENTO_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class TransacaoTest {
    @Inject
    Fixtures fixtures;
    IdPagamento umPagamento;
    Set<Categoria> categorias;
    Transacao umaTransacao;
    ItemPago umItemPago;

    IdTransacao umIdTransacao;

    @BeforeEach
    void setUp() {
        umPagamento = new IdPagamento();
        categorias = fixtures.categorias();
        umaTransacao = new Transacao(umPagamento, categorias);

//        comandoRemoveItemPago = fixtures.comandoRemoveItemPagoIdentico(comandoAdicionaItemPago);
//        comandoAlteraItemPago = fixtures.comandoAlteraItemPagoIdentico(comandoAdicionaItemPago);
//        comandoAtualizaCategoria = fixtures.comandoAtualizaCategoria(umIdTransacao.id());
    }

    @Test
    void criaTransacaoComSucesso() {
        assertNotNull(umaTransacao.id());
        assertEquals(umPagamento, umaTransacao.pagamento());
        assertEquals(categorias, umaTransacao.categorias());
    }

    @Test
    void criaTransacaoPagamentoNuloComErro() {
        umPagamento = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Transacao(umPagamento, categorias));

        assertEquals(TRANSACAO_PAGAMENTO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void adicionaItemPagoComSucesso() {
        var umaDescricao = fixtures.umaStringAleatoria();
        var umaQuantidade = fixtures.umInteiroAleatorio();
        var umTipoUnidade = "UN";
        var umValor = fixtures.floatPositivo();
        var categorias = fixtures.categoriasString();

        umaTransacao.adicionaItemMoedaPadrao(
                umaDescricao,
                umaQuantidade,
                umTipoUnidade,
                umValor,
                categorias);

        assertEquals(1, umaTransacao.itens().size());
        var item = umaTransacao.itens().stream().toList().get(0);

        assertEquals(umaDescricao, item.descricao());
        assertEquals(umaQuantidade, item.multiplicador().floatValue());
        assertEquals(umTipoUnidade, item.tipoUnidadeMedida().name());
        assertEquals(umValor, item.valorUnidade().floatValue());
        assertEquals(categorias, item.todasCategorias().stream().map(Categoria::name).collect(Collectors.toSet()));
    }

//    @Test
//    void removeItemPagoComSucesso() {
//        assertEquals(1, transacao.itens().size());
//        transacao.processaComando(comandoAdicionaItemPago)
//                .stage(this::finalizaProcessamento);
//        assertEquals(2, transacao.itens().size());
//
//        transacao.processaComando(comandoRemoveItemPago)
//                .stage(this::finalizaProcessamento);
//
//        assertEquals(comandoRemoveItemPago.idTransacao, transacao.id().id());
//        assertEquals(1, transacao.itens().size());
//    }
//
//    @Test
//    void removeItemPagoNaoExistenteComErro() {
//        var erro = assertThrows(IllegalStateException.class,
//                () -> transacao.processaComando(comandoRemoveItemPago));
//
//        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
//    }
//
//    @Test
//    void alteraItemPagoComSucesso() {
//        transacao.processaComando(comandoAdicionaItemPago)
//                .stage(this::finalizaProcessamento);
//
//        transacao.processaComando(comandoAlteraItemPago)
//                .stage(this::finalizaProcessamento);
//
//        assertEquals(comandoAlteraItemPago.idTransacao, transacao.id().id());
//        assertEquals(2, transacao.itens().size());
//        assertEquals(comandoAlteraItemPago.descricaoNova, transacao.itens().get(1).descricao());
//        assertEquals(comandoAlteraItemPago.quantidadeNova, transacao.itens().get(1).multiplicador().intValue());
//        assertEquals(comandoAlteraItemPago.valorUnidadeNova, transacao.itens().get(1).valorMonetarioUnidade().valor().floatValue());
//    }
//
//    @Test
//    void alteraItemPagoInexistenteComErro() {
//        var erro = assertThrows(IllegalStateException.class,
//                () -> transacao.processaComando(comandoAlteraItemPago));
//
//        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
//    }
//
//    @Test
//    void alteraItemPagoNovoItemInvalidoComErro() {
//        transacao.processaComando(comandoAdicionaItemPago)
//                .stage(this::finalizaProcessamento);
//        comandoAlteraItemPago.descricaoNova = null;
//
//        var erro = assertThrows(IllegalArgumentException.class,
//                () -> transacao.processaComando(comandoAlteraItemPago));
//
//        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
//    }
//
//    @Test
//    void atualizaCategoriaComSucesso() {
//        transacao.processaComando(comandoAtualizaCategoria)
//                .stage(this::finalizaProcessamento);
//
//        assertTrue(comandoAtualizaCategoria.categorias.containsAll(
//                transacao.categorias().stream()
//                        .map(Enum::name)
//                        .collect(Collectors.toSet())));
//    }
}
