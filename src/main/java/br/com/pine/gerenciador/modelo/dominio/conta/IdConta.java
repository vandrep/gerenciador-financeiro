package br.com.pine.gerenciador.modelo.dominio.conta;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_CONTA_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_CONTA_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_CONTA_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoVazio;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaComprimentoArgumento;

public class IdConta implements ObjetoDeValor {
    private String id;

    public IdConta(String umIdConta) {
        this.setId(umIdConta);
    }

    public String identificador() {
        return id;
    }

    private void setId(String umId) {
        validaArgumentoNaoNulo(umId, ID_CONTA_NULO);
        validaArgumentoNaoVazio(umId, ID_CONTA_VAZIO);
        validaComprimentoArgumento(umId, 36, ID_CONTA_TAMANHO_INVALIDO);

        this.id = umId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdConta that = (IdConta) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
