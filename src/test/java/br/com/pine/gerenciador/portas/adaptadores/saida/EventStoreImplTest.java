package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.Fixtures;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
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
class EventStoreImplTest {
    @Inject
    EventStoreImpl repositorio;
    @Inject
    Fixtures fixtures;

    @BeforeEach
    void setUp() {
    }

    @Test
    void armazenaPrimeiroEventoDoIdComSucesso() {
        var transacao = Transacao.cria(
                fixtures.umIdPagamento(),
                fixtures.categorias());

        repositorio.armazenaNovosEventos(
                transacao.idTransacao().id(),
                transacao.alteracoes())
                .stage(this::finaliza);

        var eventos = repositorio.buscaEventosPorIdStream(
                transacao.idTransacao().id())
                .stage(this::finaliza);

        assertEquals(1, eventos.size());

        TransacaoCriada transacaoCriada = (TransacaoCriada) eventos.get(0).evento();
        assertEquals(transacao.idTransacao().toString(), transacaoCriada.getId());
        assertEquals(
                transacao.categorias(),
                transacaoCriada.getCategoriasList().stream()
                        .map(Categoria::valueOf)
                        .collect(Collectors.toSet()));
    }

    @Test
    void armazenaSegundoEventoDoIdComSucesso() {
        var transacao = Transacao.cria(
                fixtures.umIdPagamento(),
                fixtures.categorias());

        repositorio.armazenaNovosEventos(
                transacao.idTransacao().id(),
                transacao.alteracoes())
                .stage(this::finaliza);

        var umId = transacao.idTransacao().id();

        var transacaoRecriada = Transacao.instancia(
                repositorio.buscaEventosPorIdStream(umId).stage(this::finaliza));

        var umaDescricao = fixtures.umaStringAleatoria();
        var umaQuantidade = fixtures.umInteiroAleatorio();
        var umTipoUnidade = "UN";
        var umValor = fixtures.floatPositivo();
        var categorias = fixtures.categoriasString();

        transacaoRecriada.adicionaItemMoedaPadrao(
                umaDescricao,
                umaQuantidade,
                umTipoUnidade,
                umValor,
                categorias);

        repositorio.armazenaNovosEventos(
                transacaoRecriada.idTransacao().id(),
                transacaoRecriada.alteracoes()).stage(this::finaliza);

        var eventos = repositorio.buscaEventosPorIdStream(umId)
                .stage(this::finaliza);

        assertEquals(2, eventos.size());
//        var transacaoFinalizada = new Transacao(eventos);

//        var transacaoFinalizada = eventos.get(0).getItemAdicionado();
        assertEquals(1, transacaoRecriada.itens().size());
        var itemDaLista = transacaoRecriada.itens().stream().toList().get(0);
        assertEquals(umaDescricao, itemDaLista.descricao());
        assertEquals(umaQuantidade, itemDaLista.multiplicador().floatValue());
        assertEquals(umTipoUnidade, itemDaLista.tipoUnidadeMedida().name());
        assertEquals(umValor, itemDaLista.valorUnidade().floatValue());
        assertEquals(categorias, itemDaLista.todasCategorias().stream().map(Categoria::name).collect(Collectors.toSet()));
    }

    private <X> X finaliza(Uni<X> umaUni) {
        return umaUni.subscribe().withSubscriber(UniAssertSubscriber.create()).awaitItem().assertCompleted().getItem();
    }

    private <X> List<X> finaliza(Multi<X> umaMulti) {
        return umaMulti.subscribe().withSubscriber(AssertSubscriber.create(10)).awaitCompletion().getItems();
    }
}