package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategoria;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriaAtualizada;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemPagoRemovido;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.PagamentoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_ENTIDADE_INVALIDA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_DATA_NULA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_ID_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_ID_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_BENEFICIARIO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_BENEFICIARIO_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_FORNECEDOR_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_NOME_FORNECEDOR_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TRANSACAO_VALOR_NEGATIVO;

public class Transacao extends RaizAgregado {
    private IdTransacao idTransacao;
    private Date dataDeInclusao;
    private float valor;
    private String nomeDoPagador;
    private String nomeDoRecebedor;
    private List<ItemPago> listaItemPago;
    private IdPagamento idPagamento;
    private Set<Categoria> conjuntoCategoria;

    public String getIdTransacao() {
        return this.idTransacao.id();
    }

    public Date getDataDeInclusao() {
        return dataDeInclusao;
    }

    public float getValor() {
        if (this.listaItemPago.isEmpty()) {
            return valor;
        }
        return listaItemPago.stream()
                .map(ItemPago::getValorUnidade)
                .reduce(0.0f, Float::sum);
    }

    public String getNomeDoPagador() {
        return nomeDoPagador;
    }

    public String getNomeDoRecebedor() {
        return nomeDoRecebedor;
    }

    public List<ItemPago> getListaItemPago() {
        return listaItemPago;
    }

    public IdPagamento getIdPagamento() {
        return idPagamento;
    }

    public Set<Categoria> getConjuntoCategoria() {
        return conjuntoCategoria;
    }


    protected Transacao(String umIdEntidade) {
        this.setIdTransacao(umIdEntidade);
        this.setConjuntoItemPago();
    }

    private List<EventoDeDominio> processa(CriaTransacao umComando) {
        validaData(umComando.data);
        validaValor(umComando.valor);
        validaNomeFornecedor(umComando.nomeDoPagador);
        validaNomeBeneficiario(umComando.nomeDoRecebedor);

        return List.of(new TransacaoCriada(umComando, this.getIdTransacao()));
    }

    private List<EventoDeDominio> processa(AdicionaItemPago umComando) {
        validaIdEntidade(this.getIdTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                UnidadeMedida.valueOf(umComando.unidadeMedida),
                umComando.valorUnidade);

        return List.of(new ItemPagoAdicionado(umComando));
    }

    private List<EventoDeDominio> processa(RemoveItemPago umComando) {
        validaIdEntidade(this.getIdTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        var itemARemover = new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                UnidadeMedida.valueOf(umComando.unidadeMedida),
                umComando.valorUnidade);

        if (!this.listaItemPago.contains(itemARemover)) {
            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
        }
        return List.of(new ItemPagoRemovido(umComando));
    }

    private List<EventoDeDominio> processa(AlteraItemPago umComando) {
        validaIdEntidade(this.getIdTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        var removeItemPago = new RemoveItemPago();
        removeItemPago.idTransacao = umComando.idTransacao;
        removeItemPago.descricao = umComando.descricaoAnterior;
        removeItemPago.quantidade = umComando.quantidadeAnterior;
        removeItemPago.unidadeMedida = umComando.unidadeMedidaAnterior;
        removeItemPago.valorUnidade = umComando.valorUnidadeAnterior;
        var listaEventos = new ArrayList<>(processaComando(removeItemPago));

        var adicionaItemPago = new AdicionaItemPago();
        adicionaItemPago.idTransacao = umComando.idTransacao;
        adicionaItemPago.descricao = umComando.descricaoNova;
        adicionaItemPago.quantidade = umComando.quantidadeNova;
        adicionaItemPago.unidadeMedida = umComando.unidadeMedidaNova;
        adicionaItemPago.valorUnidade = umComando.valorUnidadeNova;
        listaEventos.addAll(processaComando(adicionaItemPago));

        return listaEventos;
    }

    private List<EventoDeDominio> processa(AdicionaPagamento umComando) {
        validaIdEntidade(this.getIdTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        return List.of(new PagamentoAdicionado(umComando));
    }

    private List<EventoDeDominio> processa(AtualizaCategoria umComando) {
        validaIdEntidade(this.getIdTransacao(), umComando.idTransacao, ID_ENTIDADE_INVALIDA.mensagem);

        return List.of(new CategoriaAtualizada(umComando));
    }

    private void aplica(TransacaoCriada umEvento) {
        this.setDataDeInclusao(umEvento.data);
        this.setValor(umEvento.valor);
        this.setNomeDoPagador(umEvento.nomeFornecedor);
        this.setNomeDoRecebedor(umEvento.nomeBeneficiario);
        this.setConjuntoItemPago();
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
        this.setIdPagamento(umEvento.idPagamento);
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

    private void validaData(Date umaData) {
        validaArgumentoNaoNulo(umaData, TRANSACAO_DATA_NULA.mensagem);
    }

    private void validaValor(float umValor) {
        validaArgumentoMaiorOuIgualA(umValor, 0.0f, TRANSACAO_VALOR_NEGATIVO.mensagem);
        this.valor = umValor;
    }

    private void validaNomeFornecedor(String umNomeFornecedor) {
        validaArgumentoNaoNulo(umNomeFornecedor, TRANSACAO_NOME_FORNECEDOR_NULO.mensagem);
        validaArgumentoNaoVazio(umNomeFornecedor, TRANSACAO_NOME_FORNECEDOR_VAZIO.mensagem);
    }

    private void validaNomeBeneficiario(String umNomeBeneficiario) {
        validaArgumentoNaoNulo(umNomeBeneficiario, TRANSACAO_NOME_BENEFICIARIO_NULO.mensagem);
        validaArgumentoNaoVazio(umNomeBeneficiario, TRANSACAO_NOME_BENEFICIARIO_VAZIO.mensagem);
    }

    private void setIdTransacao(String umIdTransacao) {
        validaArgumentoNaoNulo(umIdTransacao, TRANSACAO_ID_NULO.mensagem);
        validaArgumentoNaoVazio(umIdTransacao, TRANSACAO_ID_VAZIO.mensagem);
        this.idTransacao = new IdTransacao(umIdTransacao);
    }

    private void setDataDeInclusao(Date umaData) {
        validaData(umaData);
        this.dataDeInclusao = umaData;
    }

    private void setValor(float umValor) {
        validaValor(umValor);
        this.valor = umValor;
    }

    private void setNomeDoPagador(String umNomeFornecedor) {
        validaNomeFornecedor(umNomeFornecedor);
        this.nomeDoPagador = umNomeFornecedor;
    }

    private void setNomeDoRecebedor(String umNomeBeneficiario) {
        validaNomeBeneficiario(umNomeBeneficiario);
        this.nomeDoRecebedor = umNomeBeneficiario;
    }

    private void setConjuntoItemPago() {
        this.listaItemPago = new ArrayList<>(0);
    }

    //    TODO voltar aqui depois que o modelo estiver mais maduro
    private void setIdPagamento(IdPagamento umIdPagamento) {
        this.idPagamento = umIdPagamento;
    }

    private void setConjuntoCategoria(Set<Categoria> umConjuntoCategoria) {
        this.conjuntoCategoria = umConjuntoCategoria;
    }
}
