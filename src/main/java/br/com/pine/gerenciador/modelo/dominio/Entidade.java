package br.com.pine.gerenciador.modelo.dominio;

public abstract class Entidade extends Validador {
    private String idEntidade;

    public Entidade() {
    }

    public String getIdEntidade() {
        return this.idEntidade;
    }

    public void setIdEntidade(String umIdEntidade) {
        this.idEntidade = umIdEntidade;
    }
}
