package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Pagamento;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.MultiCollect;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class RepositorioEvento implements PanacheRepository<EventoArmazenado> {

    public Multi<List<EventoDominio>> listaPagamentos(){
        return streamAll()
                .map(EventoArmazenado::getEventoDominio)
                .group().by(EventoDominio::getIdEntidade)
                .map(Multi::collect)
                .onItem().transformToUni(MultiCollect::asList)
                .concatenate();
    }

    public Uni<List<EventoDominio>> eventosDominioDoId(String umIdEntidade) {
        return find("identidade", umIdEntidade).stream()
                .map(EventoArmazenado::getEventoDominio)
                .onCompletion().ifEmpty().failWith(new NotFoundException("Sem eventos"))
                .collect().asList();
    }

    public Uni<Void> armazena(List<EventoDominio> listaEventos, String tipoEntidade) {
        listaEventos.forEach(eventoDominio -> armazena(eventoDominio, tipoEntidade));
        return Uni.createFrom().voidItem();
    }

    public Uni<Void> armazena(EventoDominio umEvento, String tipoEntidade) {
        var eventoArmazenado = new EventoArmazenado();
        eventoArmazenado.tipoEntidade = tipoEntidade;
        eventoArmazenado.tipoEvento = umEvento.getClass().getSimpleName();
        eventoArmazenado.idEntidade = umEvento.getIdEntidade();
        eventoArmazenado.setDadosEvento(umEvento);
        return persist(eventoArmazenado).replaceWithVoid();
    }
}
