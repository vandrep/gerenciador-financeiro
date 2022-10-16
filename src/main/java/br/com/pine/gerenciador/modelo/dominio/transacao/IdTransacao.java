package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.IdEntidade;

public class IdTransacao extends IdEntidade {
    private final String id;

    public IdTransacao(String umIdTransacao) {
        this.id = umIdTransacao;
    }

    @Override
    public String id() {
        return id;
    }
}
