package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
public class EventoArmazenado {
    private static final ObjectMapper json = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .defaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"))
            .build();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public String tipoEvento;
    public String tipoEntidade;
    public String idEntidade;
    private String dadosEvento;

    public Long getId() {
        return id;
    }

    public String getIdEntidade() {
        return idEntidade;
    }

    public EventoDeDominio getEventoDominio() {
        try {
            return json.readValue(this.dadosEvento, EventoDeDominio.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDadosEvento(EventoDeDominio dadosEvento) {
        try {
            this.dadosEvento = json.writeValueAsString(dadosEvento);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
