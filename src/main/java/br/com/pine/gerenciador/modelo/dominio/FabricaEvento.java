package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.modelo.dominio.Evento;

import java.util.Objects;

public interface FabricaEvento<E> {
    <T extends Evento, O extends Object> T criaEvento(O... objetos);
}
