package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Transacao;
import br.com.pine.gerenciador.modelo.dominio.pagamento.TransacaoService;
import br.com.pine.gerenciador.portas.adaptadores.EventoArmazenado;
import br.com.pine.gerenciador.portas.adaptadores.RepositorioEvento;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransacaoApplicationService {
    @Inject
    RepositorioEvento repositorioEvento;
    @Inject
    TransacaoService transacaoService;
    private final String PAGAMENTO = "Pagamento";

    public Uni<Void> criaTransacao(CriaTransacao umComando) {
        return repositorioEvento.armazena(transacaoService.criaTransacao(umComando), PAGAMENTO);
    }

    public Uni<Void> adicionaItemPago(AdicionaItemPago umComando) {
        return repositorioEvento.eventosDominioDoId(umComando.idTransacao)
                .onItem().transform(listaEventos -> transacaoService.instanciaTransacao(listaEventos))
                .onItem().transform(pagamento -> pagamento.processaComando(umComando))
                .onItem().transformToUni(itemPagoAdicionado -> repositorioEvento.armazena(itemPagoAdicionado, PAGAMENTO));
    }

    public Multi<EventoDeDominio> listaTodos() {
        return repositorioEvento.streamAll()
                .map(EventoArmazenado::getEventoDominio);
    }

    public Multi<Transacao> listaPagamentos() {
        return repositorioEvento.listaPagamentos()
                .map(lista -> transacaoService.instanciaTransacao(lista));
    }
}
