package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.ItemPagoAdicionado;
import br.com.pine.gerenciador.modelo.dominio.pagamento.eventos.TransacaoCriada;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

@JsonTypeInfo(use = DEDUCTION)
@JsonSubTypes({@Type(ItemPagoAdicionado.class), @Type(TransacaoCriada.class)})
public abstract class EventoDominio {
    private String idEntidade;
    private LocalDateTime ocorridoEm;

    public EventoDominio() {
        this.ocorridoEm = LocalDateTime.now();
    }

    public EventoDominio(String umIdEntidade) {
        this.idEntidade = umIdEntidade;
        this.ocorridoEm = LocalDateTime.now();
    }

    public String getIdEntidade() {
        return idEntidade;
    }
    public LocalDateTime getOcorridoEm(){
        return this.ocorridoEm;
    }

    public void setIdEntidade(String umIdEntidade) {
        this.idEntidade = umIdEntidade;
    }
}
