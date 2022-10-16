package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategoria;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_ENTIDADE_INVALIDA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NOME_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_DATA_NULA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_BENEFICIARIO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_BENEFICIARIO_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_FORNECEDOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_FORNECEDOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_VALOR_NEGATIVO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class TransacaoTest {
    @Inject
    Fixtures fixtures;
    Transacao transacao;
    String umIdEntidade;
    CriaTransacao comandoCriaTransacao;
    AdicionaItemPago comandoAdicionaItemPago;
    RemoveItemPago comandoRemoveItemPago;
    AlteraItemPago comandoAlteraItemPago;
    AdicionaPagamento comandoAdicionaPagamento;
    AtualizaCategoria comandoAtualizaCategoria;

    @BeforeEach
    void setUp() {
        umIdEntidade = fixtures.umaStringAleatoria();
        comandoCriaTransacao = fixtures.criaComandoCriaTransacao();

        transacao = new Transacao(umIdEntidade);
        transacao.processaComando(comandoCriaTransacao).forEach(transacao::aplicaEvento);

        comandoAdicionaItemPago = fixtures.criaComandoAdicionaItemPago(umIdEntidade);
        comandoRemoveItemPago = fixtures.criaComandoRemoveItemPagoIdentico(comandoAdicionaItemPago);
        comandoAlteraItemPago = fixtures.criaComandoAlteraItemPagoIdentico(comandoAdicionaItemPago);
        comandoAdicionaPagamento = fixtures.criaComandoAdicionaPagamento(umIdEntidade);
        comandoAtualizaCategoria = fixtures.criaComandoAtualizaCategoria(umIdEntidade);
    }

    @Test
    void criaTransacaoComSucesso() {
        assertEquals(comandoCriaTransacao.data, transacao.getDataDeInclusao());
        assertEquals(comandoCriaTransacao.valor, transacao.getValor());
        assertEquals(comandoCriaTransacao.nomeDoPagador, transacao.getNomeDoPagador());
        assertEquals(comandoCriaTransacao.nomeDoRecebedor, transacao.getNomeDoRecebedor());
    }

    @Test
    void criaTransacaoDataNulaComErro() {
        comandoCriaTransacao.data = null;

        var transacaoComErro = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_DATA_NULA.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoValorNegativoComErro() {
        comandoCriaTransacao.valor = fixtures.valorNegativo();

        var transacaoComErro = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_VALOR_NEGATIVO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomeFornecedorNuloComErro() {
        comandoCriaTransacao.nomeDoPagador = null;

        var transacaoComErro = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_FORNECEDOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomeFornecedorVazioComErro() {
        comandoCriaTransacao.nomeDoPagador = "";

        var transacaoComErro = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_FORNECEDOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomeBeneficiarioNuloComErro() {
        comandoCriaTransacao.nomeDoRecebedor = null;

        var transacaoComErro = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_BENEFICIARIO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomBeneficiarioVazioComErro() {
        comandoCriaTransacao.nomeDoRecebedor = "";

        var transacaoComErro = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_BENEFICIARIO_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void adicionaItemPagoComSucesso() {
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);

        assertEquals(comandoAdicionaItemPago.idTransacao, transacao.getIdTransacao());
        assertEquals(comandoAdicionaItemPago.descricao, transacao.getListaItemPago().get(0).getDescricao());
        assertEquals(comandoAdicionaItemPago.quantidade, transacao.getListaItemPago().get(0).getQuantidade());
        assertEquals(comandoAdicionaItemPago.unidadeMedida, transacao.getListaItemPago().get(0).getUnidadeMedida().name());
        assertEquals(comandoAdicionaItemPago.valorUnidade, transacao.getListaItemPago().get(0).getValorUnidade());
    }

    @Test
    void adicionaItemPagoTransacaoInvalidaComErro() {
        comandoAdicionaItemPago.idTransacao = fixtures.umaStringAleatoria();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoAdicionaItemPago));

        assertEquals(ID_ENTIDADE_INVALIDA.mensagem, erro.getMessage());
    }

    @Test
    void removeItemPagoComSucesso() {
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);
        assertFalse(transacao.getListaItemPago().isEmpty());

        transacao.processaComando(comandoRemoveItemPago).forEach(transacao::aplicaEvento);

        assertEquals(comandoRemoveItemPago.idTransacao, transacao.getIdTransacao());
        assertTrue(transacao.getListaItemPago().isEmpty());
    }

    @Test
    void removeItemPagoNaoExistenteComErro() {
        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoRemoveItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }

    @Test
    void alteraItemPagoComSucesso() {
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);

        transacao.processaComando(comandoAlteraItemPago).forEach(transacao::aplicaEvento);

        assertEquals(comandoAlteraItemPago.idTransacao, transacao.getIdTransacao());
        assertEquals(1, transacao.getListaItemPago().size());
        assertEquals(comandoAlteraItemPago.descricaoNova, transacao.getListaItemPago().get(0).getDescricao());
        assertEquals(comandoAlteraItemPago.quantidadeNova, transacao.getListaItemPago().get(0).getQuantidade());
        assertEquals(comandoAlteraItemPago.unidadeMedidaNova, transacao.getListaItemPago().get(0).getUnidadeMedida().name());
        assertEquals(comandoAlteraItemPago.valorUnidadeNova, transacao.getListaItemPago().get(0).getValorUnidade());
    }

    @Test
    void alteraItemPagoInexistenteComErro() {
        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoAlteraItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }

    @Test
    void alteraItemPagoNovoItemInvalidoComErro() {
        transacao.processaComando(comandoAdicionaItemPago).forEach(transacao::aplicaEvento);
        comandoAlteraItemPago.descricaoNova = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoAlteraItemPago));

        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }

    @Test
    void adicionaPagamentoComSucesso() {
        transacao.processaComando(comandoAdicionaPagamento).forEach(transacao::aplicaEvento);

        assertEquals(comandoAdicionaPagamento.idPagamento, transacao.getIdPagamento().id());
    }

    @Test
    void atualizaCategoriaComSucesso() {
        transacao.processaComando(comandoAtualizaCategoria).forEach(transacao::aplicaEvento);

        assertEquals(comandoAtualizaCategoria.conjuntoCategoria, transacao.getConjuntoCategoria());
    }
}
