package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategoria;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriaAtualizada;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoAlterado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoRemovido;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.PagamentoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import io.smallrye.mutiny.Multi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_ENTIDADE_INVALIDA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_DESCRICAO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_DESCRICAO_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_DESCRICAO_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_DEVE_TER_PELO_MENOS_UM_ITEM_PAGO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.transacao.TipoUnidadeMedida.UN;

public class Transacao extends RaizAgregado {
    private IdTransacao idTransacao;
    private String descricao;
    private ValorMonetario valorMonetario;
    private String nomeDoPagador;
    private String nomeDoRecebedor;
    private List<ItemPago> listaItemPago;
    private IdPagamento idPagamento;
    private Set<Categoria> conjuntoCategoria;

    public Transacao() {
    }

    public IdTransacao idTransacao() {
        return this.idTransacao;
    }

    public String descricao() {
        return this.descricao;
    }

    public ValorMonetario valorMonetario() {
        return this.valorMonetario;
    }

    public String nomeDoPagador() {
        return nomeDoPagador;
    }

    public String nomeDoRecebedor() {
        return nomeDoRecebedor;
    }

    public List<ItemPago> listaItemPago() {
        return listaItemPago;
    }

    public IdPagamento idPagamento() {
        return idPagamento;
    }

    public Set<Categoria> conjuntoCategoria() {
        return conjuntoCategoria;
    }

    private Multi<EventoDeDominio> processa(CriaTransacao umComando) {
        var idTransacao = new IdTransacao(UUID.randomUUID().toString());
        validaDescricao(umComando.descricao);
        new ItemPago(
                "",
                1,
                UN,
                ValorMonetario.emReal(umComando.valor));
        validaNomePagador(umComando.nomeDoPagador);
        validaNomeRecebedor(umComando.nomeDoRecebedor);

        new IdPagamento(umComando.idPagamento);

        var transacaoCriada = new TransacaoCriada(umComando, idTransacao.id());
        return Multi.createFrom().items(transacaoCriada);
    }

    private Multi<EventoDeDominio> processa(AdicionaItemPago umComando) {
        validaIdEntidade(this.idTransacao().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                TipoUnidadeMedida.valueOf(umComando.unidadeMedida),
                ValorMonetario.emReal(umComando.valorUnidade));

        return Multi.createFrom().items(new ItemPagoAdicionado(umComando));
    }

    private Multi<EventoDeDominio> processa(RemoveItemPago umComando) {
        validaIdEntidade(this.idTransacao().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        var itemARemover = new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                TipoUnidadeMedida.valueOf(umComando.unidadeMedida),
                ValorMonetario.emReal(umComando.valorUnidade));

        if (!this.listaItemPago.contains(itemARemover)) {
            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
        }

        return Multi.createFrom().items(new ItemPagoRemovido(umComando));
    }

    private Multi<EventoDeDominio> processa(AlteraItemPago umComando) {
        validaIdEntidade(this.idTransacao().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        var itemAAlterar = new ItemPago(
                umComando.descricaoAnterior,
                umComando.quantidadeAnterior,
                TipoUnidadeMedida.valueOf(umComando.unidadeMedidaAnterior),
                ValorMonetario.emReal(umComando.valorUnidadeAnterior));

        if (!this.listaItemPago.contains(itemAAlterar)) {
            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
        }

        var itemNovo = new ItemPago(
                umComando.descricaoNova,
                umComando.quantidadeNova,
                TipoUnidadeMedida.valueOf(umComando.unidadeMedidaNova),
                ValorMonetario.emReal(umComando.valorUnidadeNova));

        return Multi.createFrom().items(new ItemPagoAlterado(umComando));
    }

    private Multi<EventoDeDominio> processa(AdicionaPagamento umComando) {
        validaIdEntidade(this.idTransacao().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        return Multi.createFrom().items(new PagamentoAdicionado(umComando));
    }

    private Multi<EventoDeDominio> processa(AtualizaCategoria umComando) {
        validaIdEntidade(this.idTransacao().id(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        return Multi.createFrom().items(new CategoriaAtualizada(umComando));
    }

    private void aplica(TransacaoCriada umEvento) {
        this.setIdTransacao(new IdTransacao(umEvento.idTransacao));
        this.setDescricao(umEvento.descricao);
        this.adicionaItemPago(
                new ItemPago(
                        "",
                        1,
                        UN,
                        ValorMonetario.emReal(umEvento.valor)));
        this.setNomeDoPagador(umEvento.nomeDoPagador);
        this.setNomeDoRecebedor(umEvento.nomeDoRecebedor);
        this.setIdPagamento(new IdPagamento(umEvento.idPagamento));
    }

    private void aplica(ItemPagoAdicionado umEvento) {
        this.adicionaItemPago(new ItemPago(
                umEvento.descricao,
                umEvento.quantidade,
                umEvento.tipoUnidadeMedida,
                ValorMonetario.emReal(umEvento.valorUnidade)));
    }

    private void aplica(ItemPagoRemovido umEvento) {
        this.removeItemPago(new ItemPago(
                umEvento.descricao,
                umEvento.quantidade,
                umEvento.tipoUnidadeMedida,
                ValorMonetario.emReal(umEvento.valorUnidade)));
    }

    private void aplica(ItemPagoAlterado umEvento) {
        this.adicionaItemPago(
                new ItemPago(
                        umEvento.descricaoNova,
                        umEvento.quantidadeNova,
                        TipoUnidadeMedida.valueOf(umEvento.unidadeMedidaNova),
                        ValorMonetario.emReal(umEvento.valorUnidadeNova)));

        this.removeItemPago(
                new ItemPago(
                        umEvento.descricaoAnterior,
                        umEvento.quantidadeAnterior,
                        TipoUnidadeMedida.valueOf(umEvento.unidadeMedidaAnterior),
                        ValorMonetario.emReal(umEvento.valorUnidadeAnterior)));
    }

    private void aplica(PagamentoAdicionado umEvento) {
        this.setIdPagamento(new IdPagamento(umEvento.idPagamento));
    }

    private void aplica(CategoriaAtualizada umEvento) {
        this.setConjuntoCategoria(umEvento.conjuntoCategoria);
    }

    private void adicionaItemPago(ItemPago umItemPago) {
        if (this.listaItemPago == null) {
            this.listaItemPago = new ArrayList<>();
        }
        this.listaItemPago.add(umItemPago);
        this.atualizaValor();
    }

    private void removeItemPago(ItemPago umItemPago) {
        if (this.listaItemPago.size() == 1) {
            throw new IllegalStateException(TRANSACAO_DEVE_TER_PELO_MENOS_UM_ITEM_PAGO.mensagem);
        }

        this.listaItemPago.remove(umItemPago);

        this.atualizaValor();
    }

    private void adicionaCategoria(Categoria umaCategoria) {
        if (this.conjuntoCategoria == null) {
            this.conjuntoCategoria = new HashSet<>();
        }
        this.conjuntoCategoria.add(umaCategoria);
    }

    private void removeCategoria(Categoria umaCategoria) {
        this.conjuntoCategoria.remove(umaCategoria);

        if (this.conjuntoCategoria.isEmpty()) {
            this.conjuntoCategoria = null;
        }
    }

    private void validaDescricao(String umaDescricao) {
        validaArgumentoNaoNulo(umaDescricao, TRANSACAO_DESCRICAO_NULO.mensagem);
        validaArgumentoNaoVazio(umaDescricao, TRANSACAO_DESCRICAO_VAZIO.mensagem);
        validaComprimentoArgumento(umaDescricao, 60, TRANSACAO_DESCRICAO_TAMANHO_INVALIDO.mensagem);
    }

    private void validaNomePagador(String umNomePagador) {
        validaArgumentoNaoNulo(umNomePagador, TRANSACAO_NOME_DO_PAGADOR_NULO.mensagem);
        validaArgumentoNaoVazio(umNomePagador, TRANSACAO_NOME_DO_PAGADOR_VAZIO.mensagem);
        validaComprimentoArgumento(umNomePagador, 60, TRANSACAO_NOME_DO_PAGADOR_TAMANHO_INVALIDO.mensagem);
    }

    private void validaNomeRecebedor(String umNomeRecebedor) {
        validaArgumentoNaoNulo(umNomeRecebedor, TRANSACAO_NOME_DO_RECEBEDOR_NULO.mensagem);
        validaArgumentoNaoVazio(umNomeRecebedor, TRANSACAO_NOME_DO_RECEBEDOR_VAZIO.mensagem);
        validaComprimentoArgumento(umNomeRecebedor, 60, TRANSACAO_NOME_DO_RECEBEDOR_TAMANHO_INVALIDO.mensagem);
    }

    private void setIdTransacao(IdTransacao umIdTransacao) {
        this.idTransacao = umIdTransacao;
    }

    private void setDescricao(String umaDescricao) {
        validaArgumentoNaoNulo(umaDescricao, TRANSACAO_DESCRICAO_NULO.mensagem);
        validaArgumentoNaoVazio(umaDescricao, TRANSACAO_DESCRICAO_VAZIO.mensagem);
        this.descricao = umaDescricao;
    }

    private void atualizaValor() {
        this.valorMonetario = ValorMonetario.emReal(listaItemPago.stream()
                .map(ItemPago::valorMonetarioTotal)
                .map(ValorMonetario::valor)
                .reduce(0.0f, Float::sum));
    }

    private void setNomeDoPagador(String umNomePagador) {
        validaNomePagador(umNomePagador);
        this.nomeDoPagador = umNomePagador;
    }

    private void setNomeDoRecebedor(String umNomeRecebedor) {
        validaNomeRecebedor(umNomeRecebedor);
        this.nomeDoRecebedor = umNomeRecebedor;
    }

    private void setListaItemPago(List<ItemPago> umaListaItemPago) {
        this.listaItemPago = umaListaItemPago;
    }

    //    TODO voltar aqui depois que o modelo estiver mais maduro
    private void setIdPagamento(IdPagamento umIdPagamento) {
        this.idPagamento = umIdPagamento;
    }

    private void setConjuntoCategoria(Set<Categoria> umConjuntoCategoria) {
        this.conjuntoCategoria = umConjuntoCategoria;
    }
}
