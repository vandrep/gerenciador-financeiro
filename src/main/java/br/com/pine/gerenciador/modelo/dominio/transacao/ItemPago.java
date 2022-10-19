package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_NOME_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_UNIDADE_MEDIDA_NULA;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ITEM_PAGO_VALOR_NEGATIVO;

public class ItemPago extends ObjetoDeValor {
    private String descricao;
    private float quantidade;
    private UnidadeMedida unidadeMedida;
    private float valorUnidade;

    protected ItemPago(String umaDescricao,
                       int umaQuantidade,
                       UnidadeMedida umaUnidadeMedida,
                       float umValorUnidade) {
        this.setDescricao(umaDescricao);
        this.setQuantidade(umaQuantidade);
        this.setUnidadeMedida(umaUnidadeMedida);
        this.setValorUnidade(umValorUnidade);
    }

    public float valorTotal() {
        return quantidade * valorUnidade;
    }

    public UnidadeMedida unidadeMedida() {
        return unidadeMedida;
    }

    public String descricao() {
        return descricao;
    }

    public float valorUnidade() {
        return valorUnidade;
    }

    public float quantidade() {
        return quantidade;
    }

    private void setUnidadeMedida(UnidadeMedida umaUnidadeMedida) {
        validaArgumentoNaoNulo(umaUnidadeMedida, ITEM_PAGO_UNIDADE_MEDIDA_NULA.mensagem);
        this.unidadeMedida = umaUnidadeMedida;
    }

    private void setDescricao(String umNome) {
        validaArgumentoNaoNulo(umNome, ITEM_PAGO_NOME_NULO.mensagem);
        this.descricao = umNome;
    }

    private void setValorUnidade(float umValor) {
        validaArgumentoMaiorOuIgualA(umValor, 0.0f, ITEM_PAGO_VALOR_NEGATIVO.mensagem);
        this.valorUnidade = umValor;
    }

    private void setQuantidade(float umaQuantidade) {
        validaArgumentoMaiorOuIgualA(umaQuantidade, 1, ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem);
        this.quantidade = umaQuantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPago itemPago = (ItemPago) o;

        if (quantidade() != itemPago.quantidade()) return false;
        if (Float.compare(itemPago.valorUnidade(), valorUnidade()) != 0) return false;
        if (!descricao().equals(itemPago.descricao())) return false;
        return unidadeMedida() == itemPago.unidadeMedida();
    }

    @Override
    public int hashCode() {
        int result = descricao().hashCode();
        result = 31 * result + (quantidade() != +0.0f ? Float.floatToIntBits(quantidade()) : 0);
        result = 31 * result + unidadeMedida().hashCode();
        result = 31 * result + (valorUnidade() != +0.0f ? Float.floatToIntBits(valorUnidade()) : 0);
        return result;
    }
}
