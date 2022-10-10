package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.*;

public class ItemPago extends ObjetoDeValor {
    private String nome;
    private ValorMonetario valorMonetario;
    private int quantidade;

    public ItemPago(String umNome, ValorMonetario umValorMonetario, int umaQuantidade) {
        this.setNome(umNome);
        this.setValorMonetario(umValorMonetario);
        this.setQuantidade(umaQuantidade);
    }

    public String getNome() {
        return nome;
    }

    public ValorMonetario getValorMonetario() {
        return valorMonetario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void setNome(String umNome) {
        validaArgumentoNaoNulo(umNome, ITEM_PAGO_NOME_NULO.mensagem);
        validaArgumentoNaoVazio(umNome, ITEM_PAGO_NOME_VAZIO.mensagem);
        this.nome = umNome;
    }

    private void setValorMonetario(ValorMonetario umValorMonetario) {
        validaArgumentoNaoNulo(umValorMonetario, ITEM_PAGO_VALOR_MONETARIO_VAZIO.mensagem);
        this.valorMonetario = umValorMonetario;
    }

    private void setQuantidade(int umaQuantidade) {
        validaArgumentoMaiorOuIgualA(umaQuantidade, 1, ITEM_PAGO_QUANTIDADE_MENOR_QUE_UM.mensagem);
        this.quantidade = umaQuantidade;
    }
}
