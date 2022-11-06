package br.com.pine.gerenciador.portas.adaptadores.saida;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "idEVersaoUnico", columnNames = {"identificadorFluxo", "versaoFluxo"})})
public class EventoFluxo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Type(type = "uuid-char")
    private UUID identificadorFluxo;
    @NotNull
    private int versaoFluxo;
    @NotNull
    private byte[] dado;
    private byte[] metadado;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @NotNull
    private String tipoAgregado;
    @NotNull
    private String tipoEvento;

    public EventoFluxo() {
    }

    public EventoFluxo(UUID umIdentificadorFluxo,
                       int umaVersaoFluxo,
                       byte[] umDado,
                       String umTipoAgregado,
                       String umTipoEvento) {
        this.identificadorFluxo = umIdentificadorFluxo;
        this.versaoFluxo = umaVersaoFluxo;
        this.dado = umDado;
        this.tipoAgregado = umTipoAgregado;
        this.tipoEvento = umTipoEvento;
    }

    public EventoFluxo(UUID umIdentificadorFluxo,
                       int umaVersaoFluxo,
                       byte[] umDado,
                       String umTipoAgregado,
                       String umTipoEvento,
                       byte[] umMetadado) {
        this(umIdentificadorFluxo,
                umaVersaoFluxo,
                umDado,
                umTipoAgregado,
                umTipoEvento);
        this.metadado = umMetadado;
    }


    public long getId() {
        return id;
    }

    public UUID getIdentificadorFluxo() {
        return identificadorFluxo;
    }

    public int getVersaoFluxo() {
        return versaoFluxo;
    }

    public byte[] getDado() {
        return dado;
    }

    public byte[] getMetadado() {
        return metadado;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTipoAgregado() {
        return tipoAgregado;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }
}
