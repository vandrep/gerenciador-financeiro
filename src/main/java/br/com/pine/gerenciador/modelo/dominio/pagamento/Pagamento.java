package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.pagamento.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.pagamento.CriaPagamentoEmReal;
import br.com.pine.gerenciador.modelo.dominio.Agregado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;

public class Pagamento extends Agregado {
    private Date data;
    private ValorMonetario valorMonetario;
    private String nomeFornecedor;
    private String nomeBeneficiario;
    private List<ItemPago> listaItemPago;


    protected Pagamento() {
        super();
        this.setConjuntoItemPago();
    }

    public PagamentoEmRealCriado processa(CriaPagamentoEmReal umComando) {
        validaData(umComando.data);
        validaValorMonetario(ValorMonetario.emReal(umComando.valor));
        validaNomeFornecedor(umComando.nomeFornecedor);
        validaNomeBeneficiario(umComando.nomeBeneficiario);

        return new PagamentoEmRealCriado(umComando);
    }

    public ItemPagoAdicionado processa(AdicionaItemPago umComando) {
        new ItemPago(umComando.nome,
                ValorMonetario.emReal(umComando.valor),
                umComando.quantidade);

        return new ItemPagoAdicionado(umComando);
    }

    public void aplica(PagamentoEmRealCriado umEvento) {
        this.setData(umEvento.data);
        this.setValorMonetario(ValorMonetario.emReal(umEvento.valor));
        this.setNomeFornecedor(umEvento.nomeFornecedor);
        this.setNomeBeneficiario(umEvento.nomeBeneficiario);
        this.setConjuntoItemPago();
    }

    public void aplica(ItemPagoAdicionado umEvento) {
        this.adicionaItemPago(new ItemPago(
                umEvento.nome,
                ValorMonetario.emReal(umEvento.valorUnidade),
                umEvento.quantidade));
    }

    private void adicionaItemPago(ItemPago umItemPago) {
        this.listaItemPago.add(umItemPago);
    }

    public Date getData() {
        return data;
    }

    public ValorMonetario getValorMonetario() {
        if(this.listaItemPago.isEmpty()){
            return valorMonetario;
        }
        return ValorMonetario.emReal(
                listaItemPago.stream()
                        .map(itemPago -> itemPago.getValorMonetarioUnitario().getValor())
                        .reduce(0.0f, Float::sum));
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public List<ItemPago> getListaItemPago() {
        return listaItemPago;
    }

    private void validaData(Date umaData) {
        validaArgumentoNaoNulo(umaData, PAGAMENTO_DATA_NULA.mensagem);
    }

    private void validaValorMonetario(ValorMonetario umValorMonetario) {
        validaArgumentoNaoNulo(umValorMonetario, PAGAMENTO_VALOR_MONETARIO_NULO.mensagem);
        this.valorMonetario = umValorMonetario;
    }

    private void validaNomeFornecedor(String umNomeFornecedor) {
        validaArgumentoNaoNulo(umNomeFornecedor, PAGAMENTO_NOME_FORNECEDOR_NULO.mensagem);
        validaArgumentoNaoVazio(umNomeFornecedor, PAGAMENTO_NOME_FORNECEDOR_VAZIO.mensagem);
    }

    private void validaNomeBeneficiario(String umNomeBeneficiario) {
        validaArgumentoNaoNulo(umNomeBeneficiario, PAGAMENTO_NOME_BENEFICIARIO_NULO.mensagem);
        validaArgumentoNaoVazio(umNomeBeneficiario, PAGAMENTO_NOME_BENEFICIARIO_VAZIO.mensagem);
    }

    private void setData(Date umaData) {
        validaData(umaData);
        this.data = umaData;
    }

    private void setValorMonetario(ValorMonetario umValorMonetario) {
        validaValorMonetario(umValorMonetario);
        this.valorMonetario = umValorMonetario;
    }

    private void setNomeFornecedor(String umNomeFornecedor) {
        validaNomeFornecedor(umNomeFornecedor);
        this.nomeFornecedor = umNomeFornecedor;
    }

    private void setNomeBeneficiario(String umNomeBeneficiario) {
        validaNomeBeneficiario(umNomeBeneficiario);
        this.nomeBeneficiario = umNomeBeneficiario;
    }

    private void setConjuntoItemPago() {
        this.listaItemPago = new ArrayList<>(0);
    }
}
