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
    @Lob
    private String dadosEvento;

    public EventoArmazenado() {
    }

    public EventoArmazenado(EventoDeDominio umEventoDeDominio, String umTipoEntidade) {
        this.tipoEntidade = umTipoEntidade;
        this.tipoEvento = umEventoDeDominio.getClass().getSimpleName();
        this.setDadosEvento(umEventoDeDominio);
        this.idEntidade = umEventoDeDominio.idEntidade();
    }

    public Long getId() {
        return id;
    }

    public String getIdEntidade() {
        return idEntidade;
    }

    public String getEventoDominio() {
        return this.dadosEvento;
    }

    public void setDadosEvento(EventoDeDominio dadosEvento) {
        try {
            this.dadosEvento = json.writeValueAsString(dadosEvento);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
