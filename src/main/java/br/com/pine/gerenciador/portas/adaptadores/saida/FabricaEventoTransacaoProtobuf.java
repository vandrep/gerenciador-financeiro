package br.com.pine.gerenciador.portas.adaptadores.saida;

import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.FabricaEventoTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.IdTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.ValorItem;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class FabricaEventoTransacaoProtobuf implements FabricaEventoTransacao {

    @Override
    public EventoFluxo transacaoCriada(IdTransacao umId,
                                       IdPagamento umPagamento,
                                       Set<Categoria> categorias) {
        return new EventoFluxo(
                umId.id(),
                1,
                EventoTransacao.newBuilder()
                        .setTipo("TransacaoCriada")
                        .setOcorridoEm(Instant.now().toString())
                        .setVersaoEvento(1)
                        .setTransacaoCriada(TransacaoCriada.newBuilder()
                                .setId(umId.toString())
                                .setPagamento(umPagamento.toString())
                                .addAllCategorias(categorias.stream()
                                        .map(Categoria::name)
                                        .collect(Collectors.toSet()))
                                .build())
                        .build().toByteArray(),
                "Transacao",
                "TransacaoCriada");
    }

    @Override
    public EventoFluxo itemAdicionado(IdTransacao umId,
                                      int umaVersaoDoFluxo,
                                      String umaDescricao,
                                      ValorItem umValor,
                                      Set<Categoria> categorias) {
        return new EventoFluxo(
                umId.id(),
                umaVersaoDoFluxo,
                EventoTransacao.newBuilder()
                        .setTipo("ItemAdicionado")
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
                                .addAllCategorias(categorias.stream()
                                        .map(Categoria::name)
                                        .collect(Collectors.toSet()))
                                .build())
                        .build().toByteArray(),
                "Transacao",
                "ItemAdicionado");
    }

    @Override
    public EventoFluxo itemRemovido(IdTransacao umId,
                                    int umaVersaoDoFluxo,
                                    String umaDescricao) {
        return new EventoFluxo(
                umId.id(),
                umaVersaoDoFluxo,
                EventoTransacao.newBuilder()
                        .setTipo("ItemRemovido")
                        .setOcorridoEm(Instant.now().toString())
                        .setVersaoEvento(1)
                        .setItemRemovido(ItemRemovido.newBuilder()
                                .setDescricao(umaDescricao)
                                .build())
                        .build().toByteArray(),
                "Transacao",
                "ItemRemovido");
    }
}
