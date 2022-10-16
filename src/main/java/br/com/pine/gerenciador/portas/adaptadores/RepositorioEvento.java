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

    public Multi<List<EventoDeDominio>> listaEventoDeDominioAgrupadoPorIdEntidade() {
        return streamAll()
                .map(EventoArmazenado::getEventoDominio)
                .group().by(EventoDeDominio::getIdEntidade)
                .map(Multi::collect)
                .onItem().transformToUni(MultiCollect::asList)
                .concatenate();
    }

    public Uni<List<EventoDeDominio>> ListaEventoDeDominioDoIdEntidade(String umIdEntidade) {
        return find("identidade", umIdEntidade).stream()
                .map(EventoArmazenado::getEventoDominio)
                .onCompletion().ifEmpty().failWith(new NotFoundException("Sem eventos"))
                .collect().asList();
    }

    public Uni<Void> armazena(List<EventoDeDominio> listaEventoDeDominio, String tipoEntidade) {
        return this.persist(listaEventoDeDominio.stream().map(eventoDeDominio -> converte(eventoDeDominio, tipoEntidade)));
    }

    private EventoArmazenado converte(EventoDeDominio umEventoDeDominio, String tipoEntidade){
        var eventoArmazenado = new EventoArmazenado();
        eventoArmazenado.tipoEntidade = tipoEntidade;
        eventoArmazenado.tipoEvento = umEventoDeDominio.getClass().getSimpleName();
        eventoArmazenado.idEntidade = umEventoDeDominio.getIdEntidade();
        eventoArmazenado.setDadosEvento(umEventoDeDominio);
        return eventoArmazenado;
    }
}
