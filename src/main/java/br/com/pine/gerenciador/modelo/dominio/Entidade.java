package br.com.pine.gerenciador.modelo.dominio;

import java.util.UUID;

public abstract class Entidade extends Validador {
    private final String idEntidade;

    public Entidade(){
        this.idEntidade = UUID.randomUUID().toString();
    }

    public String getIdEntidade(){
        return this.idEntidade;
    }
}
