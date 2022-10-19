package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TransacaoServiceTest {
    @Inject
    TransacaoService transacaoService;
    @Inject
    Fixtures fixtures;

    String umIdTransacao;

    @BeforeEach
    void setUp() {
        umIdTransacao = fixtures.umaStringAleatoria();
    }

    @Test
    void criaTransacaoComEventos() {
        var pagamentoEmRealCriado = new TransacaoCriada();
        pagamentoEmRealCriado.idTransacao = umIdTransacao;
        pagamentoEmRealCriado.valor = 50.0f;
        pagamentoEmRealCriado.nomeDoPagador = "Joselito";
        pagamentoEmRealCriado.nomeDoRecebedor = "Augusto";
        pagamentoEmRealCriado.descricao = fixtures.umaStringAleatoria();
        pagamentoEmRealCriado.idPagamento = fixtures.umaStringAleatoria();

        var itemPagoAdicionado1 = new ItemPagoAdicionado();
        itemPagoAdicionado1.idEntidade = umIdTransacao;
        itemPagoAdicionado1.descricao = "Remédio";
        itemPagoAdicionado1.quantidade = 50;
        itemPagoAdicionado1.unidadeMedida = UnidadeMedida.UN;
        itemPagoAdicionado1.valorUnidade = 200.0f;

        var itemPagoAdicionado2 = new ItemPagoAdicionado();
        itemPagoAdicionado2.idEntidade = umIdTransacao;
        itemPagoAdicionado2.descricao = "Remédio";
        itemPagoAdicionado2.quantidade = 50;
        itemPagoAdicionado2.unidadeMedida = UnidadeMedida.UN;
        itemPagoAdicionado2.valorUnidade = 150.0f;

        var multiEventoDeDominio = Multi.createFrom().items(pagamentoEmRealCriado, itemPagoAdicionado1, itemPagoAdicionado2);

        var pagamento = transacaoService.instanciaTransacao(multiEventoDeDominio).await().indefinitely();

        var valor = pagamentoEmRealCriado.valor = 50.0f + itemPagoAdicionado1.valorUnidade + itemPagoAdicionado2.valorUnidade;

        assertEquals(valor, pagamento.valor());
        assertEquals(pagamentoEmRealCriado.nomeDoPagador, pagamento.nomeDoPagador());
        assertEquals(pagamentoEmRealCriado.nomeDoRecebedor, pagamento.nomeDoRecebedor());
        assertEquals(3, pagamento.listaItemPago().size());
        assertEquals(itemPagoAdicionado2.descricao, pagamento.listaItemPago().get(1).descricao());
    }
}