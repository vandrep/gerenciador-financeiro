//package br.com.pine.gerenciador.portas.adaptadores.saida;
//
//import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
//import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import java.time.Instant;
//import java.util.Collections;
//import java.util.Map;
//import java.util.UUID;
//
//public class EventoFluxoOB {
//    private long id;
//    private String aggregateType;
//    private UUID aggregateId;
//    private byte[] payload;
//    private String type;
//    private int aggregateVersion;
//    private byte[] meta;
//    private final Instant timestamp;
//
//    public EventoFluxoOB(Instant createdAt, Transacao umaTransacao) {
//        this.orderId = umaTransacao.id().toString();
//        this.timestamp = createdAt;
//
//        ObjectNode asJson = mapper.createObjectNode();
////                .put("id", order.getId())
////                .put("customerId", order.getCustomerId())
////                .put("orderDate", order.getOrderDate().toString());
//
//        ArrayNode items = asJson.putArray("eventos");
//
//        for (EventoTransacao alteracao : umaTransacao.alteracoes()) {
//            ObjectNode lineAsJon = mapper.createObjectNode()
//                    .put("evento", String.valueOf(alteracao));
//
//            items.add(lineAsJon);
//        }
//
//        this.jsonNode = asJson;
//    }
//
//    @Override
//    public String getAggregateId() {
//        return String.valueOf(orderId);
//    }
//
//    @Override
//    public String getAggregateType() {
//        return TYPE;
//    }
//
//    @Override
//    public JsonNode getPayload() {
//        return jsonNode;
//    }
//
//    @Override
//    public String getType() {
//        return EVENT_TYPE;
//    }
//
//    @Override
//    public Instant getTimestamp() {
//        return timestamp;
//    }
//
//    @Override
//    public Map<String, Object> getAdditionalFieldValues() {
//        // no additional fields
//        return Collections.emptyMap();
//    }
//}
