package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.portas.adaptadores.saida.EventoFluxo;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.UUID;
import java.util.stream.Stream;

public interface EventStore extends PanacheRepository<EventoFluxo> {
    Multi<EventoFluxo> buscaEventosPorIdStream(UUID umIdStream);

    Uni<Void> armazenaNovosEventos(UUID umIdFluxo,
                                   Stream<EventoFluxo> alteracoes);
}
