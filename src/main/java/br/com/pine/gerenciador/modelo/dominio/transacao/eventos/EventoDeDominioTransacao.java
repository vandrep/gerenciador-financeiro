package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

//@JsonTypeInfo(use = DEDUCTION)
//@JsonSubTypes({
//        @JsonSubTypes.Type(TransacaoCriada.class),
//        @JsonSubTypes.Type(ItemPagoAdicionado.class),
//        @JsonSubTypes.Type(ItemPagoRemovido.class)
//})

public interface EventoDeDominioTransacao extends EventoDeDominio {
}
