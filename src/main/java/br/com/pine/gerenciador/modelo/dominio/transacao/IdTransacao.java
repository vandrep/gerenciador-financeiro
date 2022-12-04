package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.IdAgregado;
import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import java.util.UUID;

import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_TRANSACAO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_TRANSACAO_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagemErro.ID_TRANSACAO_VAZIO;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoNulo;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaArgumentoNaoVazio;
import static br.com.pine.gerenciador.modelo.dominio.Validador.validaComprimentoArgumento;

public class IdTransacao implements IdAgregado {
    private UUID id;

    public IdTransacao() {
        this.setId(UUID.randomUUID().toString());
    }

    public IdTransacao(String umIdTransacao) {
        this.setId(umIdTransacao);
    }

    private void setId(String umId) {
        validaArgumentoNaoNulo(umId, ID_TRANSACAO_NULO);
        validaArgumentoNaoVazio(umId, ID_TRANSACAO_VAZIO);
        validaComprimentoArgumento(umId, 36, ID_TRANSACAO_TAMANHO_INVALIDO);

        this.id = UUID.fromString(umId);
    }

    public UUID id() {
        return id;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdTransacao that = (IdTransacao) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
