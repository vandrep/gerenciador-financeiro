package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.MultiCollect;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class RepositorioEvento implements PanacheRepository<EventoArmazenado> {

    public Multi<List<EventoDeDominio>> listaPagamentos() {
        return streamAll()
                .map(EventoArmazenado::getEventoDominio)
                .group().by(EventoDeDominio::getIdEntidade)
                .map(Multi::collect)
                .onItem().transformToUni(MultiCollect::asList)
                .concatenate();
    }

    public Uni<List<EventoDeDominio>> eventosDominioDoId(String umIdEntidade) {
        return find("identidade", umIdEntidade).stream()
                .map(EventoArmazenado::getEventoDominio)
                .onCompletion().ifEmpty().failWith(new NotFoundException("Sem eventos"))
                .collect().asList();
    }

    public Uni<Void> armazena(List<EventoDeDominio> listaEventos, String tipoEntidade) {
        listaEventos.forEach(eventoDominio -> armazena(eventoDominio, tipoEntidade));
        return Uni.createFrom().voidItem();
    }

    public Uni<Void> armazena(EventoDeDominio umEvento, String tipoEntidade) {
        var eventoArmazenado = new EventoArmazenado();
        eventoArmazenado.tipoEntidade = tipoEntidade;
        eventoArmazenado.tipoEvento = umEvento.getClass().getSimpleName();
        eventoArmazenado.idEntidade = umEvento.getIdEntidade();
        eventoArmazenado.setDadosEvento(umEvento);
        return persist(eventoArmazenado).replaceWithVoid();
    }
}
