package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.AssertSubscriber;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class EventStoreTransacaoTest {
    @Inject
    EventStoreTransacao repositorio;
    @Inject
    Fixtures fixtures;

    @BeforeEach
    void setUp() {
    }

    @Test
    void armazenaPrimeiroEventoDoIdComSucesso() {
        var umaTransacao = transacao();

        var eventos = repositorio.buscaEventosPorIdStream(umaTransacao.id().toString())
                .stage(this::finaliza);

        assertEquals(1, eventos.size());

        var transacaoCriada = eventos.get(0).getTransacaoCriada();
        assertEquals(umaTransacao.id().toString(), transacaoCriada.getId());
        assertEquals(
                umaTransacao.categorias(),
                transacaoCriada.getCategoriasList().stream()
                        .map(Categoria::valueOf)
                        .collect(Collectors.toSet()));
    }

    @Test
    void armazenaSegundoEventoDoIdComSucesso() {
        var umId = transacao().id().toString();

        var umaTransacaoRecriada = new Transacao(
                repositorio.buscaEventosPorIdStream(umId).stage(this::finaliza));

        var umaDescricao = fixtures.umaStringAleatoria();
        var umaQuantidade = fixtures.umInteiroAleatorio();
        var umTipoUnidade = "UN";
        var umValor = fixtures.floatPositivo();
        var categorias = fixtures.categoriasString();

        umaTransacaoRecriada.adicionaItemMoedaPadrao(
                umaDescricao,
                umaQuantidade,
                umTipoUnidade,
                umValor,
                categorias);

        repositorio.armazenaNovosEventos(
                umaTransacaoRecriada.id().toString(),
                umaTransacaoRecriada.alteracoes()).stage(this::finaliza);

        var eventos = repositorio.buscaEventosPorIdStream(umId)
                .stage(this::finaliza);

        assertEquals(2, eventos.size());
//        var transacaoFinalizada = new Transacao(eventos);

//        var transacaoFinalizada = eventos.get(0).getItemAdicionado();
        assertEquals(1, umaTransacaoRecriada.itens().size());
        var itemDaLista = umaTransacaoRecriada.itens().stream().toList().get(0);
        assertEquals(umaDescricao, itemDaLista.descricao());
        assertEquals(umaQuantidade, itemDaLista.multiplicador().floatValue());
        assertEquals(umTipoUnidade, itemDaLista.tipoUnidadeMedida().name());
        assertEquals(umValor, itemDaLista.valorUnidade().floatValue());
        assertEquals(categorias, itemDaLista.todasCategorias().stream().map(Categoria::name).collect(Collectors.toSet()));
    }

    private Transacao transacao() {
        var umaTransacao = new Transacao(
                fixtures.umIdPagamento(),
                fixtures.categorias());

        repositorio.armazenaNovosEventos(
                umaTransacao.id().toString(),
                umaTransacao.alteracoes()).stage(this::finaliza);
        return umaTransacao;
    }

    private <X> X finaliza(Uni<X> umaUni) {
        return umaUni.subscribe().withSubscriber(UniAssertSubscriber.create()).awaitItem().assertCompleted().getItem();
    }

    private <X> List<X> finaliza(Multi<X> umaMulti) {
        return umaMulti.subscribe().withSubscriber(AssertSubscriber.create(10)).awaitCompletion().getItems();
    }
}