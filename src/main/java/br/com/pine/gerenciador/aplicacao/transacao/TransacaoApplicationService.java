package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItem;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventStore;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.TipoUnidadeMedida;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import br.com.pine.gerenciador.portas.adaptadores.saida.EventStream;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransacaoApplicationService {
    @Inject
    EventStore<EventoTransacao> eventStore;

    public Uni<Void> processa(CriaTransacao umComando) {
        var novaTransacao = new Transacao(
                new IdPagamento(umComando.pagamento),
                categoriasNaoNulas(umComando.categorias));

        return eventStore.armazenaNovosEventos(
                novaTransacao.id().toString(),
                novaTransacao.alteracoes());
    }

    public Uni<Void> processa(AdicionaItem umComando) {
        return instanciaTransacao(umComando.idTransacao)
                .invoke(transacao ->
                        transacao.adicionaItemMoedaPadrao(
                                umComando.descricao,
                                umComando.quantidade,
                                umComando.tipoUnidadeMedida,
                                umComando.valorUnitario,
                                umComando.categorias))
                .stage(this::armazenaAlteracoes);
    }

    private Uni<Transacao> instanciaTransacao(String umId) {
        return eventStore.buscaEventosPorIdStream(umId)
                .collect().asList()
                .map(Transacao::new);
    }

    private Uni<Void> armazenaAlteracoes(Uni<Transacao> umaTransacao) {
        return umaTransacao
                .chain(transacao -> eventStore.armazenaNovosEventos(
                        transacao.id().toString(),
                        transacao.alteracoes()));
    }

    private Currency moedaValida(String umaMoeda) {
        try {
            return Currency.getInstance(umaMoeda);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Moeda Inválida.");
        }
    }

    private TipoUnidadeMedida unidadeMedidaNaoNulo(String umaUnidadeMedida) {
        try {
            return TipoUnidadeMedida.valueOf(umaUnidadeMedida);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de Unidade de Medida Inválido.");
        }
    }

    private Set<Categoria> categoriasNaoNulas(Set<String> categorias) {
        try {
            return categorias.stream()
                    .map(Categoria::valueOf)
                    .collect(Collectors.toSet());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria não cadastrada.");
        }
    }

//    private Multi<EventoDeDominio> processa(RemoveItemPago umComando) {
//        validaIdEntidade(this.id().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA);
//
//        var itemARemover = ItemPago.criaItemPago(
//                umComando.descricao,
//                umComando.quantidade,
//                "UN",
//                ValorMonetario.emReal(umComando.valorUnidade));
//
//        if (!this.itens.contains(itemARemover)) {
//            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
//        }
//
//        return Multi.createFrom().items(new ItemPagoRemovido(umComando));
//    }
//
//    private Multi<EventoDeDominio> processa(AlteraItemPago umComando) {
//        validaIdEntidade(this.id().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA);
//
//        var itemAAlterar = ItemPago.criaItemPago(
//                umComando.descricaoAnterior,
//                umComando.quantidadeAnterior,
//                "UN",
//                ValorMonetario.emReal(umComando.valorUnidadeAnterior));
//
//        if (!this.itens.contains(itemAAlterar)) {
//            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
//        }
//
//        var itemNovo = ItemPago.criaItemPago(
//                umComando.descricaoNova,
//                umComando.quantidadeNova,
//                "UN",
//                ValorMonetario.emReal(umComando.valorUnidadeNova));
//
//        return Multi.createFrom().items(new ItemPagoAlterado(umComando));
//    }
//
//    private Multi<EventoDeDominio> processa(AtualizaCategorias umComando) {
//        validaIdEntidade(this.id().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA);
//
//        return Multi.createFrom().items(new CategoriasAtualizadas(umComando));
//    }

}
