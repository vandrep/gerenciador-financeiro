package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
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
        var comandoCriaTransacao = new CriaTransacao();
        comandoCriaTransacao.valor = 50.0f;
        comandoCriaTransacao.nomeDoPagador = "Joselito";
        comandoCriaTransacao.nomeDoRecebedor = "Augusto";
        comandoCriaTransacao.descricao = fixtures.umaStringAleatoria();
        comandoCriaTransacao.idPagamento = fixtures.umaStringAleatoria();
        var pagamentoEmRealCriado = new TransacaoCriada(
                comandoCriaTransacao,
                umIdTransacao
        );

        var comandoAdicionaItemPago1 = new AdicionaItemPago();
        comandoAdicionaItemPago1.idTransacao = umIdTransacao;
        comandoAdicionaItemPago1.descricao = "Remédio";
        comandoAdicionaItemPago1.quantidade = 50;
        comandoAdicionaItemPago1.tipoUnidadeMedida = "UN";
        comandoAdicionaItemPago1.valorUnidade = 200.0f;
        var itemPagoAdicionado1 = new ItemPagoAdicionado(comandoAdicionaItemPago1);

        var comandoAdicionaItemPago2 = new AdicionaItemPago();
        comandoAdicionaItemPago2.idTransacao = umIdTransacao;
        comandoAdicionaItemPago2.descricao = "Remédio";
        comandoAdicionaItemPago2.quantidade = 50;
        comandoAdicionaItemPago2.tipoUnidadeMedida = "UN";
        comandoAdicionaItemPago2.valorUnidade = 150.0f;
        var itemPagoAdicionado2 = new ItemPagoAdicionado(comandoAdicionaItemPago2);

        var multiEventoDeDominio =
                Multi.createFrom().items(pagamentoEmRealCriado, itemPagoAdicionado1, itemPagoAdicionado2);

        var pagamento = transacaoService.instanciaTransacao(multiEventoDeDominio)
                .subscribe().withSubscriber(UniAssertSubscriber.create()).getItem();

        var valorTotal = pagamentoEmRealCriado.valor()
                + (itemPagoAdicionado1.valorUnidade() * itemPagoAdicionado1.quantidade())
                + (itemPagoAdicionado2.valorUnidade() * itemPagoAdicionado2.quantidade());

        assertEquals(valorTotal, pagamento.valorMonetario().valor().floatValue());
        assertEquals(pagamentoEmRealCriado.nomeDoPagador(), pagamento.nomeDoPagador());
        assertEquals(pagamentoEmRealCriado.nomeDoRecebedor(), pagamento.nomeDoRecebedor());
        assertEquals(3, pagamento.listaItemPago().size());
        assertEquals(itemPagoAdicionado2.descricao(), pagamento.listaItemPago().get(1).descricao());
    }
}