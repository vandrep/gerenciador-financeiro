package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;
import io.smallrye.mutiny.Uni;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;

public class ItemPago extends ObjetoDeValor {
    private String descricao;
    private int quantidade;
    private UnidadeMedida unidadeMedida;
    private float valorUnitario;

    protected ItemPago(String umaDescricao,
                       int umaQuantidade,
                       UnidadeMedida umaUnidadeMedida,
                       float umValorUnitario) {
        this.setDescricao(umaDescricao);
        this.setQuantidade(umaQuantidade);
        this.setUnidadeMedida(umaUnidadeMedida);
        this.setValorUnitario(umValorUnitario);
    }

    private void setUnidadeMedida(UnidadeMedida umaUnidadeMedida) {
        validaArgumentoNaoNulo(umaUnidadeMedida, ITEM_PAGO_UNIDADE_MEDIDA_NULA.mensagem);
        this.unidadeMedida = umaUnidadeMedida;
    }

    public float valorDoItem(){
        return quantidade * valorUnitario;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void setDescricao(String umNome) {
        validaArgumentoNaoNulo(umNome, ITEM_PAGO_NOME_NULO.mensagem);
        validaArgumentoNaoVazio(umNome, ITEM_PAGO_NOME_VAZIO.mensagem);
        this.descricao = umNome;
    }

    private void setValorUnitario(float umValor) {
        validaArgumentoMaiorOuIgualA(umValor, 0.0f, ITEM_PAGO_VALOR_NEGATIVO.mensagem);
        this.valorUnitario = umValor;
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
        if (Float.compare(itemPago.getValorUnitario(), getValorUnitario()) != 0) return false;
        if (!getDescricao().equals(itemPago.getDescricao())) return false;
        return getUnidadeMedida() == itemPago.getUnidadeMedida();
    }

    @Override
    public int hashCode() {
        int result = getDescricao().hashCode();
        result = 31 * result + getQuantidade();
        result = 31 * result + getUnidadeMedida().hashCode();
        result = 31 * result + (getValorUnitario() != +0.0f ? Float.floatToIntBits(getValorUnitario()) : 0);
        return result;
    }
}
