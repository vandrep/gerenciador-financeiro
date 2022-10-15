package br.com.pine.gerenciador.aplicacao.pagamento;

import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Pagamento;
import br.com.pine.gerenciador.modelo.dominio.pagamento.PagamentoService;
import br.com.pine.gerenciador.portas.adaptadores.EventoArmazenado;
import br.com.pine.gerenciador.portas.adaptadores.RepositorioEvento;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PagamentoApplicationService {
    @Inject
    RepositorioEvento repositorioEvento;
    @Inject
    PagamentoService pagamentoService;
    private final String PAGAMENTO = "Pagamento";

    public Uni<Void> criaPagamento(CriaPagamentoEmReal umComando) {
        return repositorioEvento.armazena(pagamentoService.criaPagamentoNovo(umComando), PAGAMENTO);
    }

    public Uni<Void> adicionaItemPago(AdicionaItemPago umComando) {
        return repositorioEvento.eventosDominioDoId(umComando.idPagamento)
                .onItem().transform(listaEventos -> pagamentoService.instanciaPagamento(listaEventos))
                .onItem().transform(pagamento -> pagamento.processa(umComando))
                .onItem().transformToUni(itemPagoAdicionado -> repositorioEvento.armazena(itemPagoAdicionado, PAGAMENTO));
    }

    public Multi<EventoDominio> listaTodos() {
        return repositorioEvento.streamAll()
                .map(EventoArmazenado::getEventoDominio);
    }

    public Multi<Pagamento> listaPagamentos() {
        return repositorioEvento.listaPagamentos()
                .map(lista -> pagamentoService.instanciaPagamento(lista));
    }
}
