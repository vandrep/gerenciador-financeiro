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
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_ID_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_ID_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_RECEBEDOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_DO_PAGADOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_VALOR_NEGATIVO;

public class Transacao extends RaizAgregado {
    private IdTransacao idTransacao;
    private float valor;
    private String nomeDoPagador;
    private String nomeDoRecebedor;
    private List<ItemPago> listaItemPago;
    private IdPagamento idPagamento;
    private Set<Categoria> conjuntoCategoria;

    public String idTransacao() {
        return this.idTransacao.id();
    }

    public float valor() {
        if (this.listaItemPago.isEmpty()) {
            return valor;
        }
        return listaItemPago.stream()
                .map(ItemPago::getValorUnidade)
                .reduce(0.0f, Float::sum);
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

    public Transacao() {
        this.setListaItemPago(new ArrayList<>(0));
        this.setConjuntoCategoria(new HashSet<>(0));
    }

    private Multi<EventoDeDominio> processa(CriaTransacao umComando) {
        validaValor(umComando.valor);
        validaNomePagador(umComando.nomeDoPagador);
        validaNomeRecebedor(umComando.nomeDoRecebedor);

        return Multi.createFrom().items(new TransacaoCriada(umComando, UUID.randomUUID().toString()));
    }

    private Multi<EventoDeDominio> processa(AdicionaItemPago umComando) {
        validaIdEntidade(this.idTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                UnidadeMedida.valueOf(umComando.unidadeMedida),
                umComando.valorUnidade);

        return Multi.createFrom().items(new ItemPagoAdicionado(umComando));
    }

    private Multi<EventoDeDominio> processa(RemoveItemPago umComando) {
        validaIdEntidade(this.idTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        var itemARemover = new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                UnidadeMedida.valueOf(umComando.unidadeMedida),
                umComando.valorUnidade);

        if (!this.listaItemPago.contains(itemARemover)) {
            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
        }

        return Multi.createFrom().items(new ItemPagoRemovido(umComando));
    }

    private Multi<EventoDeDominio> processa(AlteraItemPago umComando) {
        validaIdEntidade(this.idTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        var removeItemPago = new RemoveItemPago();
        removeItemPago.idTransacao = umComando.idTransacao;
        removeItemPago.descricao = umComando.descricaoAnterior;
        removeItemPago.quantidade = umComando.quantidadeAnterior;
        removeItemPago.unidadeMedida = umComando.unidadeMedidaAnterior;
        removeItemPago.valorUnidade = umComando.valorUnidadeAnterior;

        var adicionaItemPago = new AdicionaItemPago();
        adicionaItemPago.idTransacao = umComando.idTransacao;
        adicionaItemPago.descricao = umComando.descricaoNova;
        adicionaItemPago.quantidade = umComando.quantidadeNova;
        adicionaItemPago.unidadeMedida = umComando.unidadeMedidaNova;
        adicionaItemPago.valorUnidade = umComando.valorUnidadeNova;

        return Multi.createBy().concatenating()
                .streams(processaComando(removeItemPago), processaComando(adicionaItemPago));
    }

    private Multi<EventoDeDominio> processa(AdicionaPagamento umComando) {
        validaIdEntidade(this.idTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        return Multi.createFrom().items(new PagamentoAdicionado(umComando));
    }

    private Multi<EventoDeDominio> processa(AtualizaCategoria umComando) {
        validaIdEntidade(this.idTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        return Multi.createFrom().items(new CategoriaAtualizada(umComando));
    }

    private void aplica(TransacaoCriada umEvento) {
        this.setIdTransacao(umEvento.idEntidade);
        this.setValor(umEvento.valor);
        this.setNomeDoPagador(umEvento.nomeDoPagador);
        this.setNomeDoRecebedor(umEvento.nomeDoRecebedor);
    }

    private void aplica(ItemPagoAdicionado umEvento) {
        this.adicionaItemPago(new ItemPago(
                umEvento.descricao,
                umEvento.quantidade,
                umEvento.unidadeMedida,
                umEvento.valorUnidade));
    }

    private void aplica(ItemPagoRemovido umEvento) {
        this.removeItemPago(new ItemPago(
                umEvento.descricao,
                umEvento.quantidade,
                umEvento.unidadeMedida,
                umEvento.valorUnidade));
    }

    private void aplica(PagamentoAdicionado umEvento) {
        this.setIdPagamento(new IdPagamento(umEvento.idPagamento));
    }

    private void aplica(CategoriaAtualizada umEvento) {
        this.setConjuntoCategoria(umEvento.conjuntoCategoria);
    }

    private void adicionaItemPago(ItemPago umItemPago) {
        this.listaItemPago.add(umItemPago);
    }

    private void removeItemPago(ItemPago umItemPago) {
        this.listaItemPago.remove(umItemPago);
    }

    private void validaValor(float umValor) {
        validaArgumentoMaiorOuIgualA(umValor, 0.0f, TRANSACAO_VALOR_NEGATIVO.mensagem);
        this.valor = umValor;
    }

    private void validaNomePagador(String umNomePagador) {
        validaArgumentoNaoNulo(umNomePagador, TRANSACAO_NOME_DO_PAGADOR_NULO.mensagem);
        validaArgumentoNaoVazio(umNomePagador, TRANSACAO_NOME_DO_PAGADOR_VAZIO.mensagem);
    }

    private void validaNomeRecebedor(String umNomeRecebedor) {
        validaArgumentoNaoNulo(umNomeRecebedor, TRANSACAO_NOME_DO_RECEBEDOR_NULO.mensagem);
        validaArgumentoNaoVazio(umNomeRecebedor, TRANSACAO_NOME_DO_RECEBEDOR_VAZIO.mensagem);
    }

    private void setIdTransacao(String umIdTransacao) {
        validaArgumentoNaoNulo(umIdTransacao, TRANSACAO_ID_NULO.mensagem);
        validaArgumentoNaoVazio(umIdTransacao, TRANSACAO_ID_VAZIO.mensagem);
        this.idTransacao = new IdTransacao(umIdTransacao);
    }

    private void setValor(float umValor) {
        validaValor(umValor);
        this.valor = umValor;
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
