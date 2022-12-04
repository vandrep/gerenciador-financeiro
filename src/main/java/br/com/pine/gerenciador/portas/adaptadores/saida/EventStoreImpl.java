package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.gerenciador.modelo.dominio.EventStore;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import java.util.stream.Stream;

@ApplicationScoped
public class EventStoreImpl implements EventStore {

    @Override
    public Multi<EventoFluxo> buscaEventosPorIdStream(UUID umIdStream) {
        return stream("identificadorFluxo", umIdStream)
                .stage(this::validaMultiVazia);
    }

    @Override
    public Uni<Void> armazenaNovosEventos(UUID umIdFluxo,
                                          Stream<EventoFluxo> alteracoes) {
        return Panache.withTransaction(() ->
                        Multi.createFrom().items(alteracoes)
                                .invoke(eventoFluxo ->
                                        recuperaVersaoMaisRecente(umIdFluxo)
                                                .invoke(versaoDoFluxoNaStore -> validaVersao(versaoDoFluxoNaStore, eventoFluxo.getVersaoFluxo())))
                                .onItem().call(this::persist)
                                .collect().asList())
                .replaceWithVoid();
    }

    private void validaVersao(Integer versaoNaStore, int versaoDoEvento) {
        if (versaoNaStore > versaoDoEvento) {
            throw new ConcurrentModificationException("Nova versão da Stream");
        }
    }

    private Uni<Integer> recuperaVersaoMaisRecente(UUID umId) {
        return this.find("select max(EventoFluxo.versaoFluxo) from EventoFluxo where identificadorFluxo = ?1", umId)
                .firstResult()
                .map(EventoFluxo::getVersaoFluxo)
                .onFailure().invoke(Throwable::printStackTrace)
                .onFailure().recoverWithItem(0);
    }

    private Multi<EventoFluxo> validaMultiVazia(Multi<EventoFluxo> multiEventoArmazenado) {
        return multiEventoArmazenado.onCompletion()
                .ifEmpty().failWith(new NotFoundException("Sem eventos"));
    }
}
