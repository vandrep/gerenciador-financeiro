package br.com.pine.gerenciador.modelo.dominio;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Agregado extends Entidade{

    public List<EventoDominio> processaComando(Comando umComando) {
        try {
            try {
                return (List<EventoDominio>) getClass().getMethod("processa", umComando.getClass()).invoke(this, umComando);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Agregado> T aplicaEvento(EventoDominio eventoDominio) {
        try {
            getClass().getMethod("aplica", eventoDominio.getClass()).invoke(this, eventoDominio);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof IllegalArgumentException) {
                throw new IllegalArgumentException(e.getTargetException().getMessage());
            }
            throw new RuntimeException(e);
        }
        return (T)this;
    }
}
