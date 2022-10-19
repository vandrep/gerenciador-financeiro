package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.util.Objects;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NOME_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_UNIDADE_MEDIDA_NULA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_VALOR_MONETARIO_NULO;

public class ItemPago extends ObjetoDeValor {
    private String descricao;
    private float quantidade;
    private TipoUnidadeMedida tipoUnidadeMedida;
    private ValorMonetario valorMonetarioUnidade;
    private ValorMonetario valorMonetarioTotal;

    protected ItemPago(String umaDescricao,
                       int umaQuantidade,
                       TipoUnidadeMedida umTipoUnidadeMedida,
                       ValorMonetario umValorMonetarioUnidade) {
        this.setDescricao(umaDescricao);
        this.setQuantidade(umaQuantidade);
        this.setUnidadeMedida(umTipoUnidadeMedida);
        this.setValorMonetarioUnidade(umValorMonetarioUnidade);
        this.setValorTotal();
    }

    public ValorMonetario valorMonetarioTotal() {
        return valorMonetarioTotal;
    }

    public TipoUnidadeMedida tipoUnidadeMedida() {
        return tipoUnidadeMedida;
    }

    public String descricao() {
        return descricao;
    }

    public ValorMonetario valorMonetarioUnidade() {
        return valorMonetarioUnidade;
    }

    public float quantidade() {
        return quantidade;
    }

    private void setUnidadeMedida(TipoUnidadeMedida umTipoUnidadeMedida) {
        validaArgumentoNaoNulo(umTipoUnidadeMedida, ITEM_PAGO_UNIDADE_MEDIDA_NULA.mensagem);
        this.tipoUnidadeMedida = umTipoUnidadeMedida;
    }

    private void setDescricao(String umNome) {
        validaArgumentoNaoNulo(umNome, ITEM_PAGO_NOME_NULO.mensagem);
        this.descricao = umNome;
    }

    private void setValorMonetarioUnidade(ValorMonetario umValorMonetario) {
        validaArgumentoNaoNulo(umValorMonetario, ITEM_PAGO_VALOR_MONETARIO_NULO.mensagem);
        this.valorMonetarioUnidade = umValorMonetario;
    }

    private void setQuantidade(float umaQuantidade) {
        validaArgumentoMaiorOuIgualA(umaQuantidade, 1, ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem);
        this.quantidade = umaQuantidade;
    }

    private void setValorTotal() {
        this.valorMonetarioTotal = ValorMonetario.emReal(valorMonetarioUnidade.valor() * quantidade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPago itemPago = (ItemPago) o;

        if (Float.compare(itemPago.quantidade, quantidade) != 0) return false;
        if (!Objects.equals(descricao, itemPago.descricao)) return false;
        if (tipoUnidadeMedida != itemPago.tipoUnidadeMedida) return false;
        if (!valorMonetarioUnidade.equals(itemPago.valorMonetarioUnidade)) return false;
        return valorMonetarioTotal.equals(itemPago.valorMonetarioTotal);
    }

    @Override
    public int hashCode() {
        int result = descricao != null ? descricao.hashCode() : 0;
        result = 31 * result + (quantidade != +0.0f ? Float.floatToIntBits(quantidade) : 0);
        result = 31 * result + tipoUnidadeMedida.hashCode();
        result = 31 * result + valorMonetarioUnidade.hashCode();
        result = 31 * result + valorMonetarioTotal.hashCode();
        return result;
    }
}
