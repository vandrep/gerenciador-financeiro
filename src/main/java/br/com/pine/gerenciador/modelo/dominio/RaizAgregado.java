package br.com.pine.gerenciador.modelo.dominio;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RaizAgregado extends Entidade{

    public List<EventoDeDominio> processaComando(Comando umComando) {
        try {
            try {
                var metodoProcessa = getClass().getDeclaredMethod("processa", umComando.getClass());
                metodoProcessa.setAccessible(true);
                return (List<EventoDeDominio>) metodoProcessa.invoke(this, umComando);
//                return (List<EventoDominio>) getClass().getMethod("processa", umComando.getClass()).invoke(this, umComando);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException) e.getCause();
                }
                if (e.getCause() instanceof IllegalStateException) {
                    throw (IllegalStateException) e.getCause();
                }
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends RaizAgregado> T aplicaEvento(EventoDeDominio eventoDeDominio) {
        try {
            var metodoAplica = getClass().getDeclaredMethod("aplica", eventoDeDominio.getClass());
            metodoAplica.setAccessible(true);
            metodoAplica.invoke(this, eventoDeDominio);
//            getClass().getMethod("aplica", eventoDominio.getClass()).invoke(this, eventoDominio);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) e.getCause();
            }
            if (e.getCause() instanceof IllegalStateException) {
                throw (IllegalStateException) e.getCause();
            }
            throw new RuntimeException(e);
        }
        return (T)this;
    }
}
