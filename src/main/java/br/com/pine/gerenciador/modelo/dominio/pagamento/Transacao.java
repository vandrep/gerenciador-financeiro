package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.Agregado;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.ItemPagoRemovido;
import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.TransacaoCriada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;

public class Transacao extends Agregado {
    private Date dataDeInclusao;
    private float valor;
    private String nomeDoPagador;
    private String nomeDoRecebedor;
    private List<ItemPago> listaItemPago;
    private List<Parcela> listaParcela;


    protected Transacao(String umIdEntidade) {
        super(umIdEntidade);
        this.setConjuntoItemPago();
    }

    public TransacaoCriada processa(CriaTransacao umComando) {
        validaData(umComando.data);
        validaValor(umComando.valor);
        validaNomeFornecedor(umComando.nomeFornecedor);
        validaNomeBeneficiario(umComando.nomeBeneficiario);

        return new TransacaoCriada(umComando);
    }

    public ItemPagoAdicionado processa(AdicionaItemPago umComando) {
        if (!this.getIdEntidade().equals(umComando.idEntidade)) {
            throw new IllegalStateException(TRANSACAO_INVALIDA.mensagem);
        }
        new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                UnidadeMedida.valueOf(umComando.unidadeMedida),
                umComando.valorUnidade);

        return new ItemPagoAdicionado(umComando);
    }

    public ItemPagoRemovido processa(RemoveItemPago umComando) {
        if (!this.getIdEntidade().equals(umComando.idEntidade)) {
            throw new IllegalStateException(TRANSACAO_INVALIDA.mensagem);
        }

        var itemARemover = new ItemPago(
                umComando.descricao,
                umComando.quantidade,
                UnidadeMedida.valueOf(umComando.unidadeMedida),
                umComando.valorUnidade);

        if (!this.listaItemPago.contains(itemARemover)) {
            throw new IllegalStateException(ITEM_PAGO_NAO_EXISTE_NA_TRANSACAO.mensagem);
        }
        return new ItemPagoRemovido(umComando);
    }

    public List<EventoDominio> processa(AlteraItemPago umComando) {
        var listaEventos = new ArrayList<EventoDominio>();

        var removeItemPago = new RemoveItemPago();
        removeItemPago.idEntidade = umComando.idEntidade;
        removeItemPago.descricao = umComando.descricaoAnterior;
        removeItemPago.quantidade = umComando.quantidadeAnterior;
        removeItemPago.unidadeMedida = umComando.unidadeMedidaAnterior;
        removeItemPago.valorUnidade = umComando.valorUnidadeAnterior;
        listaEventos.add(processa(removeItemPago));

        var adicionaItemPago = new AdicionaItemPago();
        adicionaItemPago.idEntidade = umComando.idEntidade;
        adicionaItemPago.descricao = umComando.descricaoNova;
        adicionaItemPago.quantidade = umComando.quantidadeNova;
        adicionaItemPago.unidadeMedida = umComando.unidadeMedidaNova;
        adicionaItemPago.valorUnidade = umComando.valorUnidadeNova;
        listaEventos.add(processa(adicionaItemPago));

        return listaEventos;
    }

    public void aplica(TransacaoCriada umEvento) {
        this.setDataDeInclusao(umEvento.data);
        this.setValor(umEvento.valor);
        this.setNomeDoPagador(umEvento.nomeFornecedor);
        this.setNomeDoRecebedor(umEvento.nomeBeneficiario);
        this.setConjuntoItemPago();
    }

    public void aplica(ItemPagoAdicionado umEvento) {
        this.adicionaItemPago(new ItemPago(
                umEvento.descricao,
                umEvento.quantidade,
                umEvento.unidadeMedida,
                umEvento.valorUnidade));
    }

    private void adicionaItemPago(ItemPago umItemPago) {
        this.listaItemPago.add(umItemPago);
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
}
