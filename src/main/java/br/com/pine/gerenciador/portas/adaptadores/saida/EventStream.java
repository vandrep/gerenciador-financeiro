package br.com.pine.gerenciador.portas.adaptadores.saida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class EventStream {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String idStream;
    private int versaoStream;
    @Lob
    private byte[] dadosEvento;

    public EventStream() {
    }

    public EventStream(String umIdAgregado,
                       int umaVersao,
                       byte[] umEvento) {
        this.setIdStream(umIdAgregado);
        this.setVersaoStream(umaVersao);
        this.setDadosEvento(umEvento);
    }

    private void setDadosEvento(byte[] umEvento) {
        this.dadosEvento = umEvento;
    }

    private void setVersaoStream(int umaVersao) {
        this.versaoStream = umaVersao;
    }

    private void setIdStream(String umIdAgregado) {
        this.idStream = umIdAgregado;
    }


    public Long getId() {
        return this.id;
    }

    public String getIdStream() {
        return this.idStream;
    }

    public int getVersaoStream() {
        return versaoStream;
    }

    public byte[] getEventoDominio() {
        return this.dadosEvento;
    }
}
