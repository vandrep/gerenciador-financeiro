package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.util.UUID;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_PAGAMENTO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_PAGAMENTO_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_PAGAMENTO_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoVazio;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaComprimentoArgumento;

public class IdPagamento implements ObjetoDeValor {
    private String id;

    public IdPagamento() {
        this.setId(UUID.randomUUID().toString());
    }

    public IdPagamento(String umIdPagamento) {
        this.setId(umIdPagamento);
    }

    private void setId(String umId) {
        validaArgumentoNaoNulo(umId, ID_PAGAMENTO_NULO);
        validaArgumentoNaoVazio(umId, ID_PAGAMENTO_VAZIO);
        validaComprimentoArgumento(umId, 36, ID_PAGAMENTO_TAMANHO_INVALIDO);

        this.id = umId;
    }

    @Override
    public String toString() {
        return id;
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
