package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;

public enum EventoFluxoParaEventoDominio {
    TRANSACAO_CRIADA("TransacaoCriada"){
        @Override
        public GeneratedMessageV3 evento(byte[] dado){
            try {
                return EventoTransacao.parseFrom(dado).getTransacaoCriada();
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
        }
    },
    ITEM_ADICIONADO("ItemAdicionado"){
        @Override
        public GeneratedMessageV3 evento(byte[] dado){
            try {
                return EventoTransacao.parseFrom(dado).getItemAdicionado();
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
        }
    };

//    String tipoAgregado;
    String tipoEvento;
//
    EventoFluxoParaEventoDominio(String umTipoEvento){
        this.tipoEvento = umTipoEvento;
    }

//    public Converter de(String umTipoEvento){
//        return this.
//    }

    public GeneratedMessageV3 evento(byte[] dado){
        return null;
    }
}
