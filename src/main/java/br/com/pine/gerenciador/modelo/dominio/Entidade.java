package br.com.pine.gerenciador.modelo.dominio;

public abstract class Entidade extends Validador {
    private final String idEntidade;

    public Entidade(String umIdEntidade) {
        this.idEntidade = umIdEntidade;
    }

    public String getIdEntidade() {
        return this.idEntidade;
    }
}
