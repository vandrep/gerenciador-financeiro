package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.aplicacao.transacao.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.RemoveItemPago;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class TransacaoTest {
    @Inject
    Fixtures fixtures;

    String umIdEntidade;
    CriaTransacao comandoCriaTransacao;
    AdicionaItemPago comandoAdicionaItemPago;
    RemoveItemPago comandoRemoveItemPago;

    @BeforeEach
    void setUp() {
        umIdEntidade = fixtures.umaStringAleatoria();
        comandoCriaTransacao = fixtures.criaComandoCriaTransacao();
        comandoAdicionaItemPago = fixtures.criaComandoAdicionaItemPago(umIdEntidade);
        comandoRemoveItemPago = fixtures.criaComandoRemoveItemPagoIdentico(comandoAdicionaItemPago);
    }

    @Test
    void processaCriaTransacaoEmRealComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        var evento = transacao.processa(comandoCriaTransacao);

        assertEquals(comandoCriaTransacao.idEntidade, evento.getIdEntidade());
        assertEquals(comandoCriaTransacao.data, evento.data);
        assertEquals(comandoCriaTransacao.valor, evento.valor);
        assertEquals(comandoCriaTransacao.nomeFornecedor, evento.nomeFornecedor);
        assertEquals(comandoCriaTransacao.nomeBeneficiario, evento.nomeBeneficiario);
    }

    @Test
    void processaCriaTransacaoDataNulaComErro() {
        comandoCriaTransacao.data = null;

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processa(comandoCriaTransacao));

        assertEquals(TRANSACAO_DATA_NULA.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoValorNegativoComErro() {
        comandoCriaTransacao.valor = fixtures.valorNegativo();

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processa(comandoCriaTransacao));

        assertEquals(TRANSACAO_VALOR_NEGATIVO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomeFornecedorNuloComErro() {
        comandoCriaTransacao.nomeFornecedor = null;

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processa(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_FORNECEDOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomeFornecedorVazioComErro() {
        comandoCriaTransacao.nomeFornecedor = "";

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processa(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_FORNECEDOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomeBeneficiarioNuloComErro() {
        comandoCriaTransacao.nomeBeneficiario = null;

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processa(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_BENEFICIARIO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaTransacaoNomBeneficiarioVazioComErro() {
        comandoCriaTransacao.nomeBeneficiario = "";

        var transacao = new Transacao(umIdEntidade);
        var erro = assertThrows(IllegalArgumentException.class,
                () -> transacao.processa(comandoCriaTransacao));

        assertEquals(TRANSACAO_NOME_BENEFICIARIO_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void processaAdicionaItemPagoComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        transacao.aplica(transacao.processa(comandoCriaTransacao));

        var evento = transacao.processa(comandoAdicionaItemPago);

        assertEquals(comandoAdicionaItemPago.idEntidade, evento.getIdEntidade());
        assertEquals(comandoAdicionaItemPago.descricao, evento.descricao);
        assertEquals(comandoAdicionaItemPago.quantidade, evento.quantidade);
        assertEquals(comandoAdicionaItemPago.unidadeMedida, evento.unidadeMedida.name());
        assertEquals(comandoAdicionaItemPago.valorUnidade, evento.valorUnidade);
    }

    @Test
    void processaAdicionaItemPagoTransacaoInvalidaComErro() {
        var transacao = new Transacao(umIdEntidade);
        comandoAdicionaItemPago.idEntidade = fixtures.umaStringAleatoria();

        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processa(comandoAdicionaItemPago));

        assertEquals(TRANSACAO_INVALIDA.mensagem, erro.getMessage());
    }

    @Test
    void processaRemoveItemPagoComSucesso() {
        var transacao = new Transacao(umIdEntidade);
        transacao.aplica(transacao.processa(comandoCriaTransacao));
        transacao.aplica(transacao.processa(comandoAdicionaItemPago));

        var evento = transacao.processa(comandoRemoveItemPago);

        assertEquals(comandoRemoveItemPago.idEntidade, evento.getIdEntidade());
        assertEquals(comandoRemoveItemPago.descricao, evento.descricao);
        assertEquals(comandoRemoveItemPago.quantidade, evento.quantidade);
        assertEquals(comandoRemoveItemPago.unidadeMedida, evento.unidadeMedida);
        assertEquals(comandoRemoveItemPago.valorUnidade, evento.valorUnidade);
    }

    @Test
    void processaRemoveItemPagoNaoExistenteComErro() {
        var transacao = new Transacao(umIdEntidade);
        transacao.aplica(transacao.processa(comandoCriaTransacao));

        var erro = assertThrows(IllegalStateException.class,
                () -> transacao.processa(comandoRemoveItemPago));

        assertEquals(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem, erro.getMessage());
    }
}
