package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_PAGAMENTO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_PAGAMENTO_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_PAGAMENTO_VAZIO;

public class IdPagamento extends ObjetoDeValor {
    private String id;

    public IdPagamento(String umIdPagamento) {
        this.setId(umIdPagamento);
    }

    public String identificador() {
        return id;
    }

    private void setId(String umId) {
        validaArgumentoNaoNulo(umId, ID_PAGAMENTO_NULO.mensagem);
        validaArgumentoNaoVazio(umId, ID_PAGAMENTO_VAZIO.mensagem);
        validaComprimentoArgumento(umId, 36, ID_PAGAMENTO_TAMANHO_INVALIDO.mensagem);

        this.id = umId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdPagamento that = (IdPagamento) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
