package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import br.com.pine.gerenciador.portas.adaptadores.saida.EventoFluxo;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface EventStore<T> extends PanacheRepository<EventoFluxo> {
    Multi<T> buscaEventosPorIdStream(String umIdStream);

    Uni<List<EventoFluxo>> armazenaNovosEventos(String umIdStream, List<EventoTransacao> alteracoes);
}
