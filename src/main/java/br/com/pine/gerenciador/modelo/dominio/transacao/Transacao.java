package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import br.com.pine.gerenciador.portas.adaptadores.saida.EventoFluxo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.TRANSACAO_PAGAMENTO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoVazio;
import static br.com.pine.gerenciador.modelo.dominio.transacao.Categoria.SEM_CATEGORIA;

public class Transacao extends RaizAgregado {
    private IdPagamento pagamento;
    private final Set<ItemPago> itens = new HashSet<>();
    private Set<Categoria> categorias = new HashSet<>();


    public static Transacao cria(IdPagamento umPagamento,
                                 Set<Categoria> categorias) {
        Transacao transacao = new Transacao();
        transacao.aplica(evento.transacaoCriada(
                new IdTransacao(),
                transacao.pagamentoValido(umPagamento),
                transacao.categoriasValidas(categorias)));
        return transacao;
    }

    public static Transacao instancia(List<EventoFluxo> eventos) {
        var transacao = new Transacao();
        eventos.forEach(transacao::atualiza);
        return transacao;
    }

    public void adicionaItemMoedaPadrao(String umaDescricao,
                                        float umaQuantidade,
                                        String umTipoUnidade,
                                        float umValor,
                                        Set<String> categorias) {
        this.adicionaItem(
                new ItemPago(
                        umaDescricao,
                        new ValorItem(
                                new Quantidade(
                                        BigDecimal.valueOf(umaQuantidade),
                                        TipoUnidadeMedida.valueOf(umTipoUnidade)),
                                new ValorMonetario(
                                        Currency.getInstance("BRL"),
                                        BigDecimal.valueOf(umValor))),
                        categorias.stream()
                                .map(Categoria::valueOf)
                                .collect(Collectors.toSet())));
    }

    private void adicionaItem(ItemPago umItem) {
        this.aplica(evento.itemAdicionado(
                idTransacao(),
                proximaVersaoDoAgregado(),
                umItem.descricao(),
                umItem.valorItem(),
                umItem.todasCategorias()));
    }

    public void removeItem(String umaDescricao) {
        this.aplica(evento.itemRemovido(
                idTransacao(),
                proximaVersaoDoAgregado(),
                umaDescricao));
    }

    public Set<ItemPago> itens() {
        return Collections.unmodifiableSet(this.itens);
    }

    public IdPagamento pagamento() {
        return this.pagamento;
    }

    public Set<Categoria> categorias() {
        return Collections.unmodifiableSet(this.categorias);
    }

    public void when(TransacaoCriada umEvento) {
        setId(new IdTransacao(umEvento.getId()));
        setPagamento(new IdPagamento(umEvento.getPagamento()));
        setCategorias(umEvento.getCategoriasList().stream()
                .map(Categoria::valueOf)
                .collect(Collectors.toSet()));
    }

    public void when(ItemAdicionado umEvento) {
        itens.add(
                new ItemPago(
                        umEvento.getDescricao(),
                        new ValorItem(
                                new Quantidade(
                                        BigDecimal.valueOf(umEvento.getValorItem().getQuantidade().getMultiplicador()),
                                        TipoUnidadeMedida.valueOf(umEvento.getValorItem().getQuantidade().getTipoUnidadeMedida())),
                                new ValorMonetario(
                                        Currency.getInstance(umEvento.getValorItem().getValorUnitario().getMoeda()),
                                        BigDecimal.valueOf(umEvento.getValorItem().getValorUnitario().getValor()))),
                        umEvento.getCategoriasList().stream()
                                .map(Categoria::valueOf)
                                .collect(Collectors.toSet())));
    }

    public void when(ItemRemovido umEvento) {
        itens.removeIf(item -> item.descricao().equals(umEvento.getDescricao()));
    }

    private Transacao() {
    }

    public IdTransacao idTransacao() {
        return (IdTransacao) this.idAgregado();
    }

    private IdPagamento pagamentoValido(IdPagamento umPagamento) {
        validaArgumentoNaoNulo(umPagamento, TRANSACAO_PAGAMENTO_NULO);
        validaArgumentoNaoVazio(umPagamento.toString(), TRANSACAO_PAGAMENTO_NULO);
        return umPagamento;
    }

    private Set<Categoria> categoriasValidas(Set<Categoria> categorias) {
        validaArgumentoNaoNulo(categorias, TRANSACAO_PAGAMENTO_NULO);
        validaArgumentoNaoVazio(categorias.toString(), TRANSACAO_PAGAMENTO_NULO);
        return categorias;
    }

    private void setCategorias(Set<Categoria> categorias) {
        if (categorias.isEmpty()) {
            categorias.add(SEM_CATEGORIA);
        } else {
            categorias = categorias.stream()
                    .filter(categoria -> categoria != SEM_CATEGORIA)
                    .collect(Collectors.toSet());
        }
        this.categorias = categorias;
    }

    private void setId(IdTransacao umIdTransacao) {
        this.setIdAgregado(umIdTransacao);
    }

    private void setPagamento(IdPagamento umIdPagamento) {
        this.pagamento = umIdPagamento;
    }
}
