package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ITEM_PAGO_NOME_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ITEM_PAGO_DESCRICAO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ITEM_PAGO_DESCRICAO_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ITEM_PAGO_DESCRICAO_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoVazio;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaComprimentoArgumento;

public class ItemPago implements ObjetoDeValor {
    private String descricao;
    private ValorItem valorItem;
    private Set<Categoria> categorias;

    protected ItemPago(String umaDescricao,
                       ValorItem umValorItem,
                       Set<Categoria> categorias) {
        this.setDescricao(umaDescricao);
        this.setValorItem(umValorItem);
        this.setCategorias(categorias);
    }

    public String descricao() {
        return this.descricao;
    }

    public ValorItem valorItem() {
        return this.valorItem;
    }

    public Set<Categoria> todasCategorias() {
        return Collections.unmodifiableSet(this.categorias());
    }

    private Set<Categoria> categorias() {
        return this.categorias;
    }

    private void setDescricao(String umaDescricao) {
        validaArgumentoNaoNulo(umaDescricao, ITEM_PAGO_DESCRICAO_NULO);
        validaArgumentoNaoVazio(umaDescricao, ITEM_PAGO_DESCRICAO_VAZIO);
        validaComprimentoArgumento(umaDescricao, 60, ITEM_PAGO_DESCRICAO_TAMANHO_INVALIDO);

        this.descricao = umaDescricao;
    }

    private void setValorItem(ValorItem umValorItem) {
        this.valorItem = umValorItem;
    }

    private void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public BigDecimal multiplicador() {
        return this.valorItem().quantidade().multiplicador();
    }

    public TipoUnidadeMedida tipoUnidadeMedida() {
        return this.valorItem().quantidade().tipoUnidadeDeMedida();
    }

    public BigDecimal valorUnidade() {
        return this.valorItem().valorUnitario().valor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPago itemPago = (ItemPago) o;

        return descricao.equals(itemPago.descricao);
    }

    @Override
    public int hashCode() {
        return descricao.hashCode();
    }
}
