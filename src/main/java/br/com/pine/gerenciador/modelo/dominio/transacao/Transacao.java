package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import br.com.pine.gerenciador.portas.adaptadores.EventoAvro;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import static br.com.pine.gerenciador.portas.adaptadores.saida.FabricaEventosTransacao.criaEvento;

public class Transacao implements RaizAgregado {
    private IdTransacao id;
    private IdPagamento pagamento;
    private final Set<ItemPago> itens = new HashSet<>();
    private Set<Categoria> categorias = new HashSet<>();

    private final List<EventoTransacao> alteracoes = new ArrayList<>();
//    private final List<EventoAvro> alteracoes = new ArrayList<>();

    public Transacao(List<EventoTransacao> eventos) {
        eventos.forEach(this::mutate);
    }

    public Transacao(IdPagamento umPagamento,
                     Set<Categoria> categorias) {
        this.aplicaNovo(
                criaEvento(
                        new IdTransacao().toString(),
                        pagamentoValido(umPagamento),
                        categorias.stream()
                                .map(Categoria::name)
                                .collect(Collectors.toSet())));
    }

//    public Transacao(IdPagamento umPagamento,
//                     Set<Categoria> categorias) {
//        this.aplicaNovo(
//                criaEventoNovo(
//                        pagamentoValido(umPagamento),
//                        categorias.stream()
//                                .map(Categoria::name)
//                                .collect(Collectors.toSet())));
//    }

    private String pagamentoValido(IdPagamento umPagamento) {
        validaArgumentoNaoNulo(umPagamento, TRANSACAO_PAGAMENTO_NULO);
        validaArgumentoNaoVazio(umPagamento.toString(), TRANSACAO_PAGAMENTO_NULO);
        return umPagamento.toString();
    }

    public void adicionaItemMoedaPadrao(String umaDescricao,
                                        float umaQuantidade,
                                        String umTipoUnidade,
                                        float umValor,
                                        Set<String> categorias) {
        this.adicionaItemPago(
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

    public void adicionaItemOutraMoeda(String umaDescricao,
                                       float umaQuantidade,
                                       String umTipoUnidade,
                                       String umaMoeda,
                                       float umValor,
                                       Set<String> categorias) {
        this.adicionaItemPago(
                new ItemPago(
                        umaDescricao,
                        new ValorItem(
                                new Quantidade(
                                        BigDecimal.valueOf(umaQuantidade),
                                        TipoUnidadeMedida.valueOf(umTipoUnidade)),
                                new ValorMonetario(
                                        Currency.getInstance(umaMoeda),
                                        BigDecimal.valueOf(umValor))),
                        categorias.stream()
                                .map(Categoria::valueOf)
                                .collect(Collectors.toSet())));
    }

    private void adicionaItemPago(ItemPago umItemPago) {
        this.aplicaNovo(
                criaEvento(
                        umItemPago.descricao(),
                        umItemPago.valorItem(),
                        umItemPago.todasCategorias().stream()
                                .map(Categoria::name)
                                .collect(Collectors.toSet())));
    }

    public List<EventoTransacao> alteracoes() {
//    public List<EventoAvro> alteracoes() {
        return Collections.unmodifiableList(alteracoes);
    }

    public void atualizaCategorias(Set<String> umasCategorias) {
    }

    public IdTransacao id() {
        return this.id;
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

    private void aplicaNovo(EventoTransacao evento) {
//    private void aplicaNovo(EventoAvro evento) {
        this.incluiAlteracao(evento);
        this.mutate(evento);
    }

    private void incluiAlteracao(EventoTransacao umEvento) {
//    private void incluiAlteracao(EventoAvro umEvento) {
        this.alteracoes.add(umEvento);
    }

    private Transacao() {
    }

    private void mutate(EventoTransacao evento) {
//        this.when((T)evento.getTipoEventoCase().deserializa(evento));
//        this.when(EventoAvro.valueOf(evento.getTipo()).deserializa(evento.));
//        var serial = EventoAvro.valueOf(evento.getTipo());
        switch (evento.getTipoEventoCase()) {
            case TRANSACAO_CRIADA -> this.when(evento.getTransacaoCriada());
            case ITEM_ADICIONADO -> this.when(evento.getItemAdicionado());
        }
    }

//    private void mutate(EventoAvro evento) {
//        try {
//            Schema schema = new Schema.Parser().parse(new File("transacao.avsc"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Class<?> clazz;
//        try {
//            clazz = Class.forName("br.com.pine.gerenciador.modelo.dominio.transacao.eventos" + "." + evento.tipoEvento);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        var alo = evento.tipoEvento;
//
//        var userDatumReader = new GenericDatumReader<GenericRecord>(clazz);
//        DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
//        User user = null;
//        while (dataFileReader.hasNext()) {
//            // Reuse user object by passing it to next(). This saves us from
//            // allocating and garbage collecting many objects for files with
//            // many items.
//            user = dataFileReader.next(user);
//            System.out.println(user);
////        switch (evento.getTipoEventoCase()) {
////            case TRANSACAO_CRIADA -> this.when(evento.getTransacaoCriada());
////            case ITEM_ADICIONADO -> this.when(evento.getItemAdicionado());
//        }
//    }

    public void when(TransacaoCriada umEvento) {
        this.setId(new IdTransacao(umEvento.getId()));
        this.setPagamento(new IdPagamento(umEvento.getPagamento()));
        this.setCategorias(umEvento.getCategoriasList().stream()
                .map(Categoria::valueOf)
                .collect(Collectors.toSet()));
    }

    private void when(ItemAdicionado umEvento) {
        this.adicionaItem(
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

//    public void removeItemPago(ItemPago umItemPago) {
//        if (this.itens.size() == 1) {
//            throw new IllegalStateException(TRANSACAO_DEVE_TER_PELO_MENOS_UM_ITEM_PAGO.mensagem);
//        }
//
//        this.itens.remove(umItemPago);
//
//        this.atualizaValor();
//    }

//    public void alteraItemPago(ItemPago itemPagoARemover, ItemPago itemPagoAAdicionar) {
//        this.adicionaItemPago(itemPagoAAdicionar);
//        this.removeItemPago(itemPagoARemover);
//    }


    private void adicionaItem(ItemPago umItemPago) {
        this.itens.add(umItemPago);
    }

//    private Set<Categoria> getCategorias() {
//        return this.categorias;
//    }


//    private void aplica(ItemPagoAdicionado umEvento) {
//        var itemAAdicionar = new ItemPago(
//                umEvento.descricao(),
//                umEvento.valor(),
//                umEvento.categorias());
//        this.adicionaItemPago(itemAAdicionar);
//    }

//    private void aplica(ItemPagoRemovido umEvento) {
//        removeItemPago(ItemPago.criaItemPago(
//                umEvento.descricao(),
//                umEvento.quantidade(),
//                "UN",
//                ValorMonetario.emReal(umEvento.valorUnidade())));
//    }
//
//    private void aplica(ItemPagoAlterado umEvento) {
//        alteraItemPago(
//                ItemPago.criaItemPago(
//                        umEvento.descricaoAnterior(),
//                        umEvento.quantidadeAnterior(),
//                        "UN",
//                        ValorMonetario.emReal(umEvento.valorUnidadeAnterior())),
//                ItemPago.criaItemPago(
//                        umEvento.descricaoNova(),
//                        umEvento.quantidadeNova(),
//                        "UN",
//                        ValorMonetario.emReal(umEvento.valorUnidadeNova())));
//    }

//    private void aplica(CategoriasAtualizadas umEvento) {
//        atualizaCategorias(umEvento.categorias());
//    }

    private void setId(IdTransacao umIdTransacao) {
        this.id = umIdTransacao;
    }

    //    TODO voltar aqui depois que o modelo estiver mais maduro
    private void setPagamento(IdPagamento umIdPagamento) {
        this.pagamento = umIdPagamento;
    }
}
