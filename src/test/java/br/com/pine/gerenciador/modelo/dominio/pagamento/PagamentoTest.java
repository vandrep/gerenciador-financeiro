package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.aplicacao.pagamento.CriaPagamentoEmReal;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class PagamentoTest {
    @Inject
    Fixtures fixtures;

    CriaPagamentoEmReal comandoCriaPagamentoEmReal;

    @BeforeEach
    void setUp() {
        comandoCriaPagamentoEmReal = fixtures.criaComandoCriaPagamentoEmReal();
    }

    @Test
    void processaCriaPagamentoEmRealComSucesso() {
        var pagamento = new Pagamento();
        var evento = pagamento.processa(comandoCriaPagamentoEmReal);

        assertEquals(comandoCriaPagamentoEmReal.idEntidade, evento.getIdEntidade());
        assertEquals(comandoCriaPagamentoEmReal.data, evento.data);
        assertEquals(comandoCriaPagamentoEmReal.valor, evento.valor);
        assertEquals(comandoCriaPagamentoEmReal.nomeFornecedor, evento.nomeFornecedor);
        assertEquals(comandoCriaPagamentoEmReal.nomeBeneficiario, evento.nomeBeneficiario);
    }

    @Test
    void processaCriaPagamentoDataNulaComErro() {
        comandoCriaPagamentoEmReal.data = null;

        var pagamento = new Pagamento();
        var erro = assertThrows(IllegalArgumentException.class,
                () -> pagamento.processa(comandoCriaPagamentoEmReal));

        assertEquals(PAGAMENTO_DATA_NULA.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaPagamentoValorNegativoComErro() {
        comandoCriaPagamentoEmReal.valor = fixtures.valorNegativo();

        var pagamento = new Pagamento();
        var erro = assertThrows(IllegalArgumentException.class,
                () -> pagamento.processa(comandoCriaPagamentoEmReal));

        assertEquals(MOEDA_MENOR_QUE_ZERO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaPagamentoNomeFornecedorNuloComErro() {
        comandoCriaPagamentoEmReal.nomeFornecedor = null;

        var pagamento = new Pagamento();
        var erro = assertThrows(IllegalArgumentException.class,
                () -> pagamento.processa(comandoCriaPagamentoEmReal));

        assertEquals(PAGAMENTO_NOME_FORNECEDOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaPagamentoNomeFornecedorVazioComErro() {
        comandoCriaPagamentoEmReal.nomeFornecedor = "";

        var pagamento = new Pagamento();
        var erro = assertThrows(IllegalArgumentException.class,
                () -> pagamento.processa(comandoCriaPagamentoEmReal));

        assertEquals(PAGAMENTO_NOME_FORNECEDOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaPagamentoNomeBeneficiarioNuloComErro() {
        comandoCriaPagamentoEmReal.nomeBeneficiario = null;

        var pagamento = new Pagamento();
        var erro = assertThrows(IllegalArgumentException.class,
                () -> pagamento.processa(comandoCriaPagamentoEmReal));

        assertEquals(PAGAMENTO_NOME_BENEFICIARIO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void processaCriaPagamentoNomBeneficiarioVazioComErro() {
        comandoCriaPagamentoEmReal.nomeBeneficiario = "";

        var pagamento = new Pagamento();
        var erro = assertThrows(IllegalArgumentException.class,
                () -> pagamento.processa(comandoCriaPagamentoEmReal));

        assertEquals(PAGAMENTO_NOME_BENEFICIARIO_VAZIO.mensagem, erro.getMessage());
    }
}