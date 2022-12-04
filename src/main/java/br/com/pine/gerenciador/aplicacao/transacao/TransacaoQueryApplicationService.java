package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.dados.DadosTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventStore;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class TransacaoQueryApplicationService {
    @Inject
    EventStore eventStore;

    public Uni<DadosTransacao> consultaTransacao(String umIdTransacao) {
        return eventStore.buscaEventosPorIdStream(UUID.fromString(umIdTransacao))
                .collect().asList()
                .map(Transacao::instancia)
                .map(DadosTransacao::new);
    }
}
