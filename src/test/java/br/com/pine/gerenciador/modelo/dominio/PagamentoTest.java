package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Pagamento;
import br.com.pine.gerenciador.modelo.dominio.pagamento.ValorMonetario;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Date;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PagamentoTest {
    @Inject
    Fixtures fixtures;
    Date umaData;
    ValorMonetario umValorMonetario;
    String umNomeFornecedor;
    String umNomeBeneficiario;

    @BeforeEach
    void setUp() {
        umaData = fixtures.umaDataAleatoria();
        umValorMonetario = fixtures.umValorMonetarioRealPositivo();
        umNomeFornecedor = fixtures.umaStringAleatoria();
        umNomeBeneficiario = fixtures.umaStringAleatoria();
    }

    @Test
    void criaPagamentoComSucesso(){
        assertDoesNotThrow(() -> new Pagamento(
                umaData,
                umValorMonetario,
                umNomeFornecedor,
                umNomeBeneficiario));
    }

    @Test
    void criaPagamentoDataNulaComErro(){
        umaData = null;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Pagamento(
                umaData,
                umValorMonetario,
                umNomeFornecedor,
                umNomeBeneficiario));
        assertEquals(PAGAMENTO_DATA_NULA.mensagem, erro.getMessage());
    }

    @Test
    void criaPagamentoValorMonetarioNuloComErro(){
        umValorMonetario = null;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Pagamento(
                        umaData,
                        umValorMonetario,
                        umNomeFornecedor,
                        umNomeBeneficiario));
        assertEquals(PAGAMENTO_VALOR_MONETARIO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaPagamentoNomeFornecedorNuloComErro(){
        umNomeFornecedor = null;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Pagamento(
                        umaData,
                        umValorMonetario,
                        umNomeFornecedor,
                        umNomeBeneficiario));
        assertEquals(PAGAMENTO_NOME_FORNECEDOR_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaPagamentoNomeFornecedorVazioComErro(){
        umNomeFornecedor = "";
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Pagamento(
                        umaData,
                        umValorMonetario,
                        umNomeFornecedor,
                        umNomeBeneficiario));
        assertEquals(PAGAMENTO_NOME_FORNECEDOR_VAZIO.mensagem, erro.getMessage());
    }

    @Test
    void criaPagamentoNomeBeneficiarioNuloComErro(){
        umNomeBeneficiario = null;
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Pagamento(
                        umaData,
                        umValorMonetario,
                        umNomeFornecedor,
                        umNomeBeneficiario));
        assertEquals(PAGAMENTO_NOME_BENEFICIARIO_NULO.mensagem, erro.getMessage());
    }

    @Test
    void criaPagamentoNomBeneficiarioVazioComErro(){
        umNomeBeneficiario = "";
        var erro = assertThrows(IllegalArgumentException.class,
                () -> new Pagamento(
                        umaData,
                        umValorMonetario,
                        umNomeFornecedor,
                        umNomeBeneficiario));
        assertEquals(PAGAMENTO_NOME_BENEFICIARIO_VAZIO.mensagem, erro.getMessage());
    }
}