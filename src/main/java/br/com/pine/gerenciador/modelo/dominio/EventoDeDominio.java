package br.com.pine.gerenciador.modelo.dominio;

import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoDeDominioTransacao;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

//@JsonTypeInfo(use = DEDUCTION)
//@JsonSubTypes({
//        @JsonSubTypes.Type(EventoDeDominioTransacao.class)
//})
public interface EventoDeDominio {
    String idEntidade();

    LocalDateTime ocorridoEm();

    int versaoDoEvento();
}
