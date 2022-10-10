package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;

@Entity
public class EventoArmazenado extends PanacheEntity {
    private static final ObjectMapper json = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .defaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"))
            .build();
    public String tipoEvento;
    public String tipoEntidade;
    public String idEntidade;
    private String dadosEvento;

    public EventoArmazenado() {
    }

    public static Uni<Void> armazenaEvento(
            String umTipoEntidade,
            String umTipoEvento,
            String umIdEntidade,
            EventoDominio umEvento) {
        var evento = new EventoArmazenado();
        evento.tipoEvento = umTipoEvento;
        evento.tipoEntidade = umTipoEntidade;
        evento.idEntidade = umIdEntidade;
        evento.setDadosEvento(umEvento);
        return evento.persist().replaceWithVoid();
    }

    public static Multi<EventoArmazenado> eventosDoId(String umIdEntidade) {
        return stream("identidade", umIdEntidade);
    }

    public EventoDominio getEventoDominio() {
        try {
            return json.readValue(this.dadosEvento, EventoDominio.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDadosEvento(EventoDominio dadosEvento) {
        try {
            this.dadosEvento = json.writeValueAsString(dadosEvento);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
