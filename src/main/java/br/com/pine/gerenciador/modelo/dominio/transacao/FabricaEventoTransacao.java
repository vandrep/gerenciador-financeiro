package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.portas.adaptadores.saida.EventoFluxo;

import java.util.Set;

public interface FabricaEventoTransacao {
    EventoFluxo transacaoCriada(IdTransacao umId,
                                IdPagamento umPagamento,
                                Set<Categoria> categorias);

    EventoFluxo itemAdicionado(IdTransacao umId,
                               int umaVersaoDoFluxo,
                               String umaDescricao,
                               ValorItem umValor,
                               Set<Categoria> categorias);

    EventoFluxo itemRemovido(IdTransacao umId,
                             int umaVersaoDoFluxo,
                             String umaDescricao);
}
