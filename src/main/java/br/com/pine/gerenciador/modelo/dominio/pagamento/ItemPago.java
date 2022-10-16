package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;

public class ItemPago extends ObjetoDeValor {
    private String descricao;
    private int quantidade;
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

    private void setUnidadeMedida(UnidadeMedida umaUnidadeMedida) {
        validaArgumentoNaoNulo(umaUnidadeMedida, ITEM_PAGO_UNIDADE_MEDIDA_NULA.mensagem);
        this.unidadeMedida = umaUnidadeMedida;
    }

    public float valorDoItem(){
        return quantidade * valorUnidade;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorUnidade() {
        return valorUnidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void setDescricao(String umNome) {
        validaArgumentoNaoNulo(umNome, ITEM_PAGO_NOME_NULO.mensagem);
        validaArgumentoNaoVazio(umNome, ITEM_PAGO_NOME_VAZIO.mensagem);
        this.descricao = umNome;
    }

    private void setValorUnidade(float umValor) {
        validaArgumentoMaiorOuIgualA(umValor, 0.0f, ITEM_PAGO_VALOR_NEGATIVO.mensagem);
        this.valorUnidade = umValor;
    }

    private void setQuantidade(int umaQuantidade) {
        validaArgumentoMaiorOuIgualA(umaQuantidade, 1, ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem);
        this.quantidade = umaQuantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPago itemPago = (ItemPago) o;

        if (getQuantidade() != itemPago.getQuantidade()) return false;
        if (Float.compare(itemPago.getValorUnidade(), getValorUnidade()) != 0) return false;
        if (!getDescricao().equals(itemPago.getDescricao())) return false;
        return getUnidadeMedida() == itemPago.getUnidadeMedida();
    }

    @Override
    public int hashCode() {
        int result = getDescricao().hashCode();
        result = 31 * result + getQuantidade();
        result = 31 * result + getUnidadeMedida().hashCode();
        result = 31 * result + (getValorUnidade() != +0.0f ? Float.floatToIntBits(getValorUnidade()) : 0);
        return result;
    }
}
