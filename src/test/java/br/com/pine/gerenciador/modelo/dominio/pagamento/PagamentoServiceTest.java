package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class PagamentoServiceTest {
    @Inject
    PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void criaPagamentoComEventos() {
        var pagamentoEmRealCriado = new PagamentoEmRealCriado();
        pagamentoEmRealCriado.data = Date.from(Instant.now());
        pagamentoEmRealCriado.valor = 50.0f;
        pagamentoEmRealCriado.nomeFornecedor = "Joselito";
        pagamentoEmRealCriado.nomeBeneficiario = "Augusto";

        var itemPagoAdicionado1 = new ItemPagoAdicionado();
        itemPagoAdicionado1.nome = "Remédio";
        itemPagoAdicionado1.quantidade = 50;
        itemPagoAdicionado1.valorUnidade = 200.0f;

        var itemPagoAdicionado2 = new ItemPagoAdicionado();
        itemPagoAdicionado2.nome = "Remédio";
        itemPagoAdicionado2.quantidade = 50;
        itemPagoAdicionado2.valorUnidade = 150.0f;

        var listaEventos = new ArrayList<EventoDominio>();
        listaEventos.add(pagamentoEmRealCriado);
        listaEventos.add(itemPagoAdicionado1);
        listaEventos.add(itemPagoAdicionado2);

        var pagamento = pagamentoService.instanciaPagamento(listaEventos);

        var valor = itemPagoAdicionado1.valorUnidade + itemPagoAdicionado2.valorUnidade;

        assertEquals(pagamentoEmRealCriado.data, pagamento.getData());
        assertEquals(valor, pagamento.getValorMonetario().getValor());
        assertEquals(pagamentoEmRealCriado.nomeFornecedor, pagamento.getNomeFornecedor());
        assertEquals(pagamentoEmRealCriado.nomeBeneficiario, pagamento.getNomeBeneficiario());
        assertEquals(2, pagamento.getListaItemPago().size());
        assertEquals(itemPagoAdicionado2.nome, pagamento.getListaItemPago().get(0).getNome());
    }
}