package br.com.pine.gerenciador.modelo.dominio.transacao;

public class ValorItem {
    private Quantidade quantidade;
    private ValorMonetario valorUnitario;

    public ValorItem(Quantidade umaQuantidade, ValorMonetario umValorUnitario) {
        this.setQuantidade(umaQuantidade);
        this.setValorUnitario(umValorUnitario);
    }

    public Quantidade quantidade() {
        return this.quantidade;
    }

    public ValorMonetario valorUnitario() {
        return this.valorUnitario;
    }

    private void setQuantidade(Quantidade umaQuantidade) {
        this.quantidade = umaQuantidade;
    }

    private void setValorUnitario(ValorMonetario umValorUnitario) {
        this.valorUnitario = umValorUnitario;
    }
}
