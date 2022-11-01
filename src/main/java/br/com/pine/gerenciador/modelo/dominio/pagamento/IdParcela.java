package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.util.UUID;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_PARCELA_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_PARCELA_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_PARCELA_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoVazio;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaComprimentoArgumento;

public class IdParcela implements ObjetoDeValor {
    private String id;

    public IdParcela() {
        this.setId(UUID.randomUUID().toString());
    }

    public String identificador() {
        return id;
    }

    private void setId(String umId) {
        validaArgumentoNaoNulo(umId, ID_PARCELA_NULO);
        validaArgumentoNaoVazio(umId, ID_PARCELA_VAZIO);
        validaComprimentoArgumento(umId, 36, ID_PARCELA_TAMANHO_INVALIDO);

        this.id = umId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdParcela that = (IdParcela) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
