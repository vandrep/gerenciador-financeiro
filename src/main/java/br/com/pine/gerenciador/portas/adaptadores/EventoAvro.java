package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

public enum EventoAvro {
    TRANSACAO_CRIADA("TransacaoCriada")
            {
                @Override
                public TransacaoCriada deserializa(byte[] dados){
                    try {
                        return TransacaoCriada.parseFrom(dados);
//                        return evento = TransacaoCriada.parseFrom(dados);
//                        umaTransacao.when(evento);
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
    public final String tipoEvento;

    EventoAvro(String umTipoEvento){
        this.tipoEvento = umTipoEvento;
    }

    public TransacaoCriada deserializa(byte[] dados){
        return null;
    }
//
//    public byte[] eventoBinario;
//
//    public EventoAvro(String tipoEvento,
//                      byte[] eventoBinario) {
//        this.tipoEvento = tipoEvento;
//        this.eventoBinario = eventoBinario;
//    }
//
//    public void mutate(Transacao umaTransacao){
//        umaTransacao.
//    }
}
