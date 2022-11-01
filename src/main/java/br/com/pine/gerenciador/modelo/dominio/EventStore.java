package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import br.com.pine.gerenciador.portas.adaptadores.saida.EventStream;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface EventStore<T> extends PanacheRepository<EventStream> {
    Multi<T> buscaEventosPorIdStream(String umIdStream);
    Uni<Void> armazenaNovosEventos(String umIdStream, List<EventoTransacao> alteracoes);
}
