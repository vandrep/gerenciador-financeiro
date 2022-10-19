package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategoria;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_ENTIDADE_INVALIDA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NOME_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_VALOR_NEGATIVO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class TransacaoTest {
    @Inject
    Fixtures fixtures;
    Transacao transacao = new Transacao();
    String umIdEntidade;
    CriaTransacao comandoCriaTransacao;
    AdicionaItemPago comandoAdicionaItemPago;
    RemoveItemPago comandoRemoveItemPago;
    AlteraItemPago comandoAlteraItemPago;
    AdicionaPagamento comandoAdicionaPagamento;
    AtualizaCategoria comandoAtualizaCategoria;

    @BeforeEach
    void setUp() {
        comandoCriaTransacao = fixtures.criaComandoCriaTransacao();

        transacao.processaComando(comandoCriaTransacao)
                .stage(this::finalizaProcessamento);

        umIdEntidade = transacao.idTransacao();

        comandoAdicionaItemPago = fixtures.criaComandoAdicionaItemPago(umIdEntidade);
        comandoRemoveItemPago = fixtures.criaComandoRemoveItemPagoIdentico(comandoAdicionaItemPago);
        comandoAlteraItemPago = fixtures.criaComandoAlteraItemPagoIdentico(comandoAdicionaItemPago);
        comandoAdicionaPagamento = fixtures.criaComandoAdicionaPagamento(umIdEntidade);
        comandoAtualizaCategoria = fixtures.criaComandoAtualizaCategoria(umIdEntidade);
    }

    @Test
    void criaTransacaoComSucesso() {
        assertEquals(comandoCriaTransacao.valor, transacao.valor());
        assertEquals(comandoCriaTransacao.nomeDoPagador, transacao.nomeDoPagador());
        assertEquals(comandoCriaTransacao.nomeDoRecebedor, transacao.nomeDoRecebedor());
    }

    @Test
    void criaTransacaoValorNegativoComErro() {
        comandoCriaTransacao.valor = fixtures.valorNegativo();
        var transacaoComErro = new Transacao();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));
        assertEquals(TRANSACAO_VALOR_NEGATIVO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomePagadorNuloComErro() {
        comandoCriaTransacao.nomeDoPagador = null;
        var transacaoComErro = new Transacao();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_DO_PAGADOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomePagadorVazioComErro() {
        comandoCriaTransacao.nomeDoPagador = "";
        var transacaoComErro = new Transacao();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_DO_PAGADOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomeRecebedorNuloComErro() {
        comandoCriaTransacao.nomeDoRecebedor = null;
        var transacaoComErro = new Transacao();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_DO_RECEBEDOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaTransacaoNomeRecebedorVazioComErro() {
        comandoCriaTransacao.nomeDoRecebedor = "";
        var transacaoComErro = new Transacao();

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacaoComErro.processaComando(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_DO_RECEBEDOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void adicionaItemPagoComSucesso() {
        transacao.processaComando(comandoAdicionaItemPago)
                .stage(this::finalizaProcessamento);

        assertEquals(comandoAdicionaItemPago.idTransacao, transacao.idTransacao());
        assertEquals(comandoAdicionaItemPago.descricao, transacao.listaItemPago().get(0).getDescricao());
        assertEquals(comandoAdicionaItemPago.quantidade, transacao.listaItemPago().get(0).getQuantidade());
        assertEquals(comandoAdicionaItemPago.unidadeMedida, transacao.listaItemPago().get(0).getUnidadeMedida().name());
        assertEquals(comandoAdicionaItemPago.valorUnidade, transacao.listaItemPago().get(0).getValorUnidade());
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
        transacao.processaComando(comandoAdicionaItemPago)
                .stage(this::finalizaProcessamento);
        assertFalse(transacao.listaItemPago().isEmpty());

        transacao.processaComando(comandoRemoveItemPago)
                .stage(this::finalizaProcessamento);

        assertEquals(comandoRemoveItemPago.idTransacao, transacao.idTransacao());
        assertTrue(transacao.listaItemPago().isEmpty());
    }

    @Test
    void removeItemPagoNaoExistenteComErro() {
        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoRemoveItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }

    @Test
    void alteraItemPagoComSucesso() {
        transacao.processaComando(comandoAdicionaItemPago)
                .stage(this::finalizaProcessamento);

        transacao.processaComando(comandoAlteraItemPago)
                .stage(this::finalizaProcessamento);

        assertEquals(comandoAlteraItemPago.idTransacao, transacao.idTransacao());
        assertEquals(1, transacao.listaItemPago().size());
        assertEquals(comandoAlteraItemPago.descricaoNova, transacao.listaItemPago().get(0).getDescricao());
        assertEquals(comandoAlteraItemPago.quantidadeNova, transacao.listaItemPago().get(0).getQuantidade());
        assertEquals(comandoAlteraItemPago.unidadeMedidaNova, transacao.listaItemPago().get(0).getUnidadeMedida().name());
        assertEquals(comandoAlteraItemPago.valorUnidadeNova, transacao.listaItemPago().get(0).getValorUnidade());
    }

    @Test
    void alteraItemPagoInexistenteComErro() {
        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processaComando(comandoAlteraItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }

    @Test
    void alteraItemPagoNovoItemInvalidoComErro() {
        transacao.processaComando(comandoAdicionaItemPago)
                .stage(this::finalizaProcessamento);
        comandoAlteraItemPago.descricaoNova = null;

        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processaComando(comandoAlteraItemPago));

        assertEquals(ITEM_PAGO_NOME_NULO.mensagem, erro.getMessage());
    }

    @Test
    void adicionaPagamentoComSucesso() {
        transacao.processaComando(comandoAdicionaPagamento)
                .stage(this::finalizaProcessamento);

        assertEquals(comandoAdicionaPagamento.idPagamento, transacao.idPagamento().id());
    }

    @Test
    void atualizaCategoriaComSucesso() {
        transacao.processaComando(comandoAtualizaCategoria)
                .stage(this::finalizaProcessamento);

        assertEquals(comandoAtualizaCategoria.conjuntoCategoria, transacao.conjuntoCategoria());
    }

    private Transacao finalizaProcessamento(Multi<EventoDeDominio> eventoDeDominioMulti) {
        // Não consegui encontrar forma melhor para reduzir esse Multi. Se tivesse alguma operação que recebesse
        // somente o accumulator no collect, ficaria melhor.
        return (Transacao) eventoDeDominioMulti
                .onItem().transform(transacao::aplicaEvento)
                .collect().last()
                .subscribe().withSubscriber(UniAssertSubscriber.create()).getItem();
    }
}
