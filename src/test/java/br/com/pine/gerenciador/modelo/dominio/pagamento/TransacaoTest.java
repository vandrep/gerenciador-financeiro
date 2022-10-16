package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TransacaoTest {
    @Inject
    Fixtures fixtures;

    String umIdEntidade;
    CriaTransacao comandoCriaTransacao;
    AdicionaItemPago comandoAdicionaItemPago;
    RemoveItemPago comandoRemoveItemPago;
    AlteraItemPago comandoAlteraItemPago;
    AdicionaPagamento comandoAdicionaPagamento;
    ConfirmaPagamentoParcela comandoConfirmaPagamentoParcela;
    AgendaPagamentoParcela comandoAgendaPagamentoParcela;
    AjustaValorParcela comandoAjustaValorParcela;
    AjustaContaParcela comandoAjustaContaParcela;

    @BeforeEach
    void setUp() {
        umIdEntidade = fixtures.umaStringAleatoria();
        comandoCriaTransacao = fixtures.criaComandoCriaTransacao();

        comandoAdicionaItemPago = fixtures.criaComandoAdicionaItemPago(umIdEntidade);
        comandoRemoveItemPago = fixtures.criaComandoRemoveItemPagoIdentico(comandoAdicionaItemPago);
        comandoAlteraItemPago = fixtures.criaComandoAlteraItemPagoIdentico(comandoAdicionaItemPago);
    }

    @Test
    void processaCriaTransacaoComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);

        assertEquals(comandoCriaTransacao.data, transacao.getDataDeInclusao());
        assertEquals(comandoCriaTransacao.valor, transacao.getValor());
        assertEquals(comandoCriaTransacao.nomeDoPagador, transacao.getNomeDoPagador());
        assertEquals(comandoCriaTransacao.nomeDoRecebedor, transacao.getNomeDoRecebedor());
    }

    @Test
    void processaCriaTransacaoDataNulaComErro() {
        comandoCriaTransacao.data = null;

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_DATA_NULA.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoValorNegativoComErro() {
        comandoCriaTransacao.valor = fixtures.valorNegativo();

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_VALOR_NEGATIVO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomeFornecedorNuloComErro() {
        comandoCriaTransacao.nomeDoPagador = null;

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_FORNECEDOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomeFornecedorVazioComErro() {
        comandoCriaTransacao.nomeDoPagador = "";

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_FORNECEDOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomeBeneficiarioNuloComErro() {
        comandoCriaTransacao.nomeDoRecebedor = null;

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_BENEFICIARIO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomBeneficiarioVazioComErro() {
        comandoCriaTransacao.nomeDoRecebedor = "";

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_BENEFICIARIO_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void processaAdicionaItemPagoComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);

        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);

        assertEquals(comandoAdicionaItemPago.idEntidade, transacao.getIdTransacao());
        assertEquals(comandoAdicionaItemPago.descricao, transacao.getListaItemPago().get(0).getDescricao());
        assertEquals(comandoAdicionaItemPago.quantidade, transacao.getListaItemPago().get(0).getQuantidade());
        assertEquals(comandoAdicionaItemPago.unidadeMedida, transacao.getListaItemPago().get(0).getUnidadeMedida().name());
        assertEquals(comandoAdicionaItemPago.valorUnidade, transacao.getListaItemPago().get(0).getValorUnidade());
    }

    @Test
    void processaAdicionaItemPagoTransacaoInvalidaComErro() {
        var transacao = new Transacao(umIdEntidade);
        comandoAdicionaItemPago.idEntidade = fixtures.umaStringAleatoria();

        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoAdicionaItemPago));

        assertEquals(TRANSACAO_INVALIDA.mensagem, erro.getMessage());
    }

    @Test
    void processaRemoveItemPagoComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);
        assertFalse(transacao.getListaItemPago().isEmpty());

        transacao.processaComando(comandoRemoveItemPago).forEach(transacao::aplicaEvento);

        assertEquals(comandoRemoveItemPago.idEntidade, transacao.getIdTransacao());
        assertTrue(transacao.getListaItemPago().isEmpty());
    }

    @Test
    void processaRemoveItemPagoNaoExistenteComErro() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);

        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoRemoveItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }

    @Test
    void processaAlteraItemPagoComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);

        transacao.processaComando(comandoAlteraItemPago).forEach(transacao::aplicaEvento);

        assertEquals(comandoAlteraItemPago.idEntidade, transacao.getIdTransacao());
        assertEquals(1, transacao.getListaItemPago().size());
        assertEquals(comandoAlteraItemPago.descricaoNova, transacao.getListaItemPago().get(0).getDescricao());
        assertEquals(comandoAlteraItemPago.quantidadeNova, transacao.getListaItemPago().get(0).getQuantidade());
        assertEquals(comandoAlteraItemPago.unidadeMedidaNova, transacao.getListaItemPago().get(0).getUnidadeMedida().name());
        assertEquals(comandoAlteraItemPago.valorUnidadeNova, transacao.getListaItemPago().get(0).getValorUnidade());
    }

    @Test
    void processaAlteraItemPagoInexistenteComErro() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);

        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoAlteraItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }

    @Test
    void processaAlteraItemPagoNovoItemInvalidoComErro() {
        var transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);
        comandoAlteraItemPago.descricaoNova = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoAlteraItemPago));

        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaAdicionaPagamentoComSucesso() {

    }

    @Test
    void processaAdicionaPagamentoTransacaoInvalidaComErro() {

    }

    @Test
    void processaConfirmaPagamentoParcelaComSucesso() {

    }

    @Test
    void processaConfirmaPagamentoParcelaInexistenteComErro() {

    }

    @Test
    void processaAgendaPagamentoParcelaComSucesso() {

    }

    @Test
    void processaAgendaPagamentoParcelaInexistenteComErro() {

    }

    @Test
    void processaAjustaValorParcelaComSucesso() {

    }

    @Test
    void processaAjustaValorParcelaInexistenteComErro() {

    }

    @Test
    void processaAjustaContaParcelaComSucesso() {

    }

    @Test
    void processaAjustaContaParcelaInexistenteComErro() {

    }
}
