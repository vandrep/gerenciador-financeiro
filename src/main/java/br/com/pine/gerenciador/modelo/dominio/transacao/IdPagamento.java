package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.IdEntidade;

public class IdPagamento extends IdEntidade {
    private String id;

    public IdPagamento(String umIdPagamento) {
        this.id = umIdPagamento;
    }

    @Override
    public String id() {
        return id;
    }
}
