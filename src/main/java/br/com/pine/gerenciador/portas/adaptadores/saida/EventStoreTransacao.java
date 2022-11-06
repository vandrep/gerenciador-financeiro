package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.gerenciador.modelo.dominio.EventStore;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import com.google.protobuf.InvalidProtocolBufferException;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventStoreTransacao implements EventStore<EventoTransacao> {
    @RequestScoped
    int versaoEsperada = 0;

    @Override
    public Multi<EventoTransacao> buscaEventosPorIdStream(String umIdStream) {
        return stream("identificadorFluxo", umIdStream)
                .stage(this::validaMultiVazia)
                .invoke(this::armazenaVersaoEsperada)
                .map(EventoFluxo::getDado)
                .map(this::traduzEvento);
    }

    @Override
    public Uni<List<EventoFluxo>> armazenaNovosEventos(String umIdStream,
                                                       List<EventoTransacao> alteracoes) {
        return Panache.withTransaction(() ->
                recuperaVersaoMaisRecente(umIdStream)
                        .onItem().transformToMulti(oq -> Multi.createFrom().iterable(alteracoes)
                                .onItem().transform(evento -> new EventoFluxo(
                                        UUID.fromString(umIdStream),
                                        versaoEsperada + 1 + alteracoes.indexOf(evento),
                                        evento.toByteArray(),
                                        Transacao.class.getSimpleName(),
                                        evento.getTipo()))
                                .onItem().call(this::persist))
                        .collect().asList());
    }

    private void armazenaVersaoEsperada(EventoFluxo umaStream) {
        versaoEsperada = (versaoEsperada < umaStream.getVersaoFluxo() ? umaStream.getVersaoFluxo() : versaoEsperada);
    }

    private Uni<Void> recuperaVersaoMaisRecente(String umId) {
        return this.stream("select max(EventStream.versaoStream) from EventStream where idStream = ?1", umId)
                .onFailure().recoverWithCompletion()
                .map(EventoFluxo::getVersaoFluxo)
                .onItem().invoke(versaoAtual -> {
                    if (versaoAtual > versaoEsperada) {
                        throw new ConcurrentModificationException("Nova versão da Stream");
                    }
                })
                .collect().asList().replaceWithVoid();
    }

    public EventoTransacao traduzEvento(byte[] umEvento) {
        try {
            return EventoTransacao.parseFrom(umEvento);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private Multi<EventoFluxo> validaMultiVazia(Multi<EventoFluxo> multiEventoArmazenado) {
        return multiEventoArmazenado.onCompletion()
                .ifEmpty().failWith(new NotFoundException("Sem eventos"));
    }

//    @Override
//    public Uni<Void> armazenaNovosEventosDaTransacao(String id,
//                                                         int versaoOriginal,
//                                                         Multi<Evento> eventos) {
//        return eventos
//                .onItem().transform(evento -> new EventStream(
//                        id,
//                        versaoOriginal,
//                        evento.toByteArray()))
//                .onItem().call(this::persist);
//    }
//
//    @Override
//    public Uni<Void> armazenaNovosEventosDaTransacao(Transacao umaTransacao) {
//        return umaTransacao.alteracoes()
//                .onItem().transform(evento -> new EventStream(
//                        umaTransacao.id().toString(),
//                        2,
//                        evento.toByteArray()))
//                .onItem().call(this::persist)
//                .collect().asList().replaceWithVoid();
//    }
}
