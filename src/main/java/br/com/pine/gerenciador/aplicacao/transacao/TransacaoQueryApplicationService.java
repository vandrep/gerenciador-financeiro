package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.dados.DadosTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventStore;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransacaoQueryApplicationService {
    @Inject
    EventStore<EventoTransacao> eventStore;

    public Uni<DadosTransacao> consultaTransacao(String umIdTransacao) {
        return eventStore.buscaEventosPorIdStream(umIdTransacao)
                .collect().asList()
                .map(Transacao::new)
                .map(DadosTransacao::new);
    }
}
