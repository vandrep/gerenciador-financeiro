package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.math.BigDecimal;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NOME_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_QUANTIDADE_NULA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_VALOR_MONETARIO_UNIDADE_NULO;

public class ItemPago extends ObjetoDeValor {
    private String descricao;
    private Quantidade quantidade;
    private ValorMonetario valorMonetarioUnidade;
    private ValorMonetario valorMonetarioTotal;

    public static ItemPago unidade(String umaDescricao,
                                   int umaQuantidade,
                                   ValorMonetario umValorMonetario) {
        return new ItemPago(
                umaDescricao,
                Quantidade.unidade(umaQuantidade),
                umValorMonetario
        );
    }

    public ValorMonetario valorMonetarioTotal() {
        return valorMonetarioTotal;
    }

    public BigDecimal multiplicador() {
        return quantidade.multiplicador();
    }

    public TipoUnidadeMedida tipoUnidadeMedida() {
        return quantidade.tipoUnidadeDeMedida();
    }

    public String descricao() {
        return descricao;
    }

    public ValorMonetario valorMonetarioUnidade() {
        return valorMonetarioUnidade;
    }

    public float valorUnidade() {
        return valorMonetarioUnidade().valor().floatValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPago itemPago = (ItemPago) o;

        if (!descricao.equals(itemPago.descricao)) return false;
        if (!quantidade.equals(itemPago.quantidade)) return false;
        if (!valorMonetarioUnidade.equals(itemPago.valorMonetarioUnidade)) return false;
        return valorMonetarioTotal.equals(itemPago.valorMonetarioTotal);
    }

    @Override
    public int hashCode() {
        int result = descricao.hashCode();
        result = 31 * result + quantidade.hashCode();
        result = 31 * result + valorMonetarioUnidade.hashCode();
        result = 31 * result + valorMonetarioTotal.hashCode();
        return result;
    }

    private ItemPago(String umaDescricao,
                     Quantidade umaQuantidade,
                     ValorMonetario umValorMonetarioUnidade) {
        this.setDescricao(umaDescricao);
        this.setQuantidade(umaQuantidade);
        this.setValorMonetarioUnidade(umValorMonetarioUnidade);
        this.setValorTotal();
    }

    private void setDescricao(String umNome) {
        validaArgumentoNaoNulo(umNome, ITEM_PAGO_NOME_NULO.mensagem);
        this.descricao = umNome;
    }

    private void setQuantidade(Quantidade umaQuantidade) {
        validaArgumentoNaoNulo(umaQuantidade, ITEM_PAGO_QUANTIDADE_NULA.mensagem);
        this.quantidade = umaQuantidade;
    }

    private void setValorMonetarioUnidade(ValorMonetario umValorMonetario) {
        validaArgumentoNaoNulo(umValorMonetario, ITEM_PAGO_VALOR_MONETARIO_UNIDADE_NULO.mensagem);
        this.valorMonetarioUnidade = umValorMonetario;
    }

    private void setValorTotal() {
        this.valorMonetarioTotal = ValorMonetario.emReal(valorMonetarioUnidade.valor().multiply(multiplicador()).floatValue());
    }
}
