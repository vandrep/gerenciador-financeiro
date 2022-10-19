package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.ObjetoDeValor;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_TRANSACAO_NULO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_TRANSACAO_TAMANHO_INVALIDO;
import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.ID_TRANSACAO_VAZIO;

public class IdTransacao extends ObjetoDeValor {
    private String id;

    public IdTransacao(String umIdTransacao) {
        this.setId(umIdTransacao);
    }

    public String id() {
        return id;
    }

    private void setId(String umId) {
        validaArgumentoNaoNulo(umId, ID_TRANSACAO_NULO.mensagem);
        validaArgumentoNaoVazio(umId, ID_TRANSACAO_VAZIO.mensagem);
        validaComprimentoArgumento(umId, 36, ID_TRANSACAO_TAMANHO_INVALIDO.mensagem);

        this.id = umId;
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
