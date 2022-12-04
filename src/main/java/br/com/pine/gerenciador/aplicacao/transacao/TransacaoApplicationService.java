package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItem;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItem;
import br.com.pine.gerenciador.modelo.dominio.EventStore;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransacaoApplicationService {
    @Inject
    EventStore eventStore;

    public Uni<Void> processa(CriaTransacao umComando) {
        return armazenaAlteracoes(
                Transacao.cria(
                        new IdPagamento(umComando.pagamento),
                        categoriasNaoNulas(umComando.categorias)));
    }

    public Uni<Void> processa(AdicionaItem umComando) {
        return instanciaTransacao(UUID.fromString(umComando.idTransacao))
                .invoke(transacao -> transacao.adicionaItemMoedaPadrao(
                        umComando.descricao,
                        umComando.quantidade,
                        umComando.tipoUnidadeMedida,
                        umComando.valorUnitario,
                        umComando.categorias))
                .chain(this::armazenaAlteracoes);
    }

    public Uni<Void> processa(RemoveItem umComando) {
        return instanciaTransacao(UUID.fromString(umComando.idTransacao))
                .invoke(transacao -> transacao.removeItem(
                        umComando.descricao))
                .chain(this::armazenaAlteracoes);
    }

    private Uni<Transacao> instanciaTransacao(UUID umIdAgregado){
        return eventStore.buscaEventosPorIdStream(umIdAgregado)
                .collect().asList()
                .map(Transacao::instancia);
    }

    private Uni<Void> armazenaAlteracoes(Transacao umaTransacao) {
        return eventStore.armazenaNovosEventos(
                        umaTransacao.idTransacao().id(),
                        umaTransacao.alteracoes());
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
