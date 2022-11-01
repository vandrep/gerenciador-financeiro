package br.com.pine.gerenciador.modelo.dominio;

public interface RaizAgregado extends Entidade{

//    public Multi<Evento> processaComando(Comando umComando) {
//        try {
//            try {
//                var metodoProcessa = getClass().getDeclaredMethod("processa", umComando.getClass());
//                metodoProcessa.setAccessible(true);
//                return (Multi<Evento>) metodoProcessa.invoke(this, umComando);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            } catch (InvocationTargetException e) {
//                if (e.getCause() instanceof IllegalArgumentException) {
//                    throw (IllegalArgumentException) e.getCause();
//                }
//                if (e.getCause() instanceof IllegalStateException) {
//                    throw (IllegalStateException) e.getCause();
//                }
//                throw new RuntimeException(e);
//            }
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public <T extends RaizAgregado> T aplicaEvento(Evento eventoDeDominio) {
//        try {
//            var metodoAplica = getClass().getDeclaredMethod("aplica", eventoDeDominio.getClass());
//            metodoAplica.setAccessible(true);
//            metodoAplica.invoke(this, eventoDeDominio);
//
//        } catch (IllegalAccessException | NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            if (e.getCause() instanceof IllegalArgumentException) {
//                throw (IllegalArgumentException) e.getCause();
//            }
//            if (e.getCause() instanceof IllegalStateException) {
//                throw (IllegalStateException) e.getCause();
//            }
//            throw new RuntimeException(e);
//        }
//        return (T)this;
//    }
}
