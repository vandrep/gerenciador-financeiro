package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.gerenciador.modelo.dominio.transacao.ValorItem;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.Transacao1;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;
import com.google.protobuf.Message;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Set;

public class FabricaEventosTransacao {
    public static EventoTransacao criaEvento(String umId,
                                             String umPagamento,
                                             Set<String> categorias) {
        return EventoTransacao.newBuilder()
                .setTipo("TransacaoCriada")
                .setOcorridoEm(Instant.now().toString())
                .setVersaoEvento(0)
                .setTransacaoCriada(TransacaoCriada.newBuilder()
                        .setId(umId)
                        .setPagamento(umPagamento)
                        .addAllCategorias(categorias)
                        .build())
                .build();
    }

    public Transacao1 criaEventoNovo(String umId,
                                     String umPagamento,
                                     Set<String> categorias) {
        var evento = Transacao1.newBuilder()
                .setPagamento(umPagamento)
                .setCategorias(categorias.stream().toList())
                .build();
        return null;
    }

    public static EventoTransacao criaEvento(String umaDescricao,
                                             ValorItem umValor,
                                             Set<String> categorias) {
        return EventoTransacao.newBuilder()
                .setTipo("ItemPagoAdicionado")
                .setOcorridoEm(Instant.now().toString())
                .setVersaoEvento(1)
                .setItemAdicionado(ItemAdicionado.newBuilder()
                        .setDescricao(umaDescricao)
                        .setValorItem(ObjetosDeValor.ValorItem.newBuilder()
                                .setQuantidade(ObjetosDeValor.Quantidade.newBuilder()
                                        .setMultiplicador(umValor.quantidade().multiplicador().floatValue())
                                        .setTipoUnidadeMedida(umValor.quantidade().tipoUnidadeDeMedida().name())
                                        .build())
                                .setValorUnitario(ObjetosDeValor.ValorMonetario.newBuilder()
                                        .setMoeda(umValor.valorUnitario().moeda().getCurrencyCode())
                                        .setValor(umValor.valorUnitario().valor().floatValue())
                                        .build())
                                .build())
                        .addAllCategorias(categorias)
                        .build())
                .build();
    }
}
