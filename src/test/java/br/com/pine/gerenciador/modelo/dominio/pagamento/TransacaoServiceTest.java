package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.TransacaoCriada;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TransacaoServiceTest {
    @Inject
    TransacaoService transacaoService;
    @Inject
    Fixtures fixtures;

    String umIdEntidade;

    @BeforeEach
    void setUp() {
        umIdEntidade = fixtures.umaStringAleatoria();
    }

    @Test
    void criaTransacaoComEventos() {
        var pagamentoEmRealCriado = new TransacaoCriada();
        pagamentoEmRealCriado.setIdEntidade(umIdEntidade);
        pagamentoEmRealCriado.data = Date.from(Instant.now());
        pagamentoEmRealCriado.valor = 50.0f;
        pagamentoEmRealCriado.nomeFornecedor = "Joselito";
        pagamentoEmRealCriado.nomeBeneficiario = "Augusto";

        var itemPagoAdicionado1 = new ItemPagoAdicionado();
        itemPagoAdicionado1.setIdEntidade(umIdEntidade);
        itemPagoAdicionado1.descricao = "Remédio";
        itemPagoAdicionado1.quantidade = 50;
        itemPagoAdicionado1.unidadeMedida = UnidadeMedida.UNIDADE;
        itemPagoAdicionado1.valorUnidade = 200.0f;

        var itemPagoAdicionado2 = new ItemPagoAdicionado();
        itemPagoAdicionado2.setIdEntidade(umIdEntidade);
        itemPagoAdicionado2.descricao = "Remédio";
        itemPagoAdicionado2.quantidade = 50;
        itemPagoAdicionado2.unidadeMedida = UnidadeMedida.UNIDADE;
        itemPagoAdicionado2.valorUnidade = 150.0f;

        var listaEventos = new ArrayList<EventoDominio>();
        listaEventos.add(pagamentoEmRealCriado);
        listaEventos.add(itemPagoAdicionado1);
        listaEventos.add(itemPagoAdicionado2);

        var pagamento = transacaoService.instanciaTransacao(listaEventos);

        var valor = itemPagoAdicionado1.valorUnidade + itemPagoAdicionado2.valorUnidade;

        assertEquals(pagamentoEmRealCriado.data, pagamento.getDataDeInclusao());
        assertEquals(valor, pagamento.getValor());
        assertEquals(pagamentoEmRealCriado.nomeFornecedor, pagamento.getNomeDoPagador());
        assertEquals(pagamentoEmRealCriado.nomeBeneficiario, pagamento.getNomeDoRecebedor());
        assertEquals(2, pagamento.getListaItemPago().size());
        assertEquals(itemPagoAdicionado2.descricao, pagamento.getListaItemPago().get(0).getDescricao());
    }
}