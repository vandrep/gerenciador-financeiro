package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.dados.DadosTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.RepositorioTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.TransacaoService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransacaoQueryApplicationService {

    @Inject
    RepositorioTransacao repositorioTransacao;

    @Inject
    TransacaoService transacaoService;

    public Multi<DadosTransacao> consultaTodasTransacoes() {
        return repositorioTransacao.buscaTodasTransacoes()
                .map(DadosTransacao::new);
    }

    public Uni<DadosTransacao> consultaTransacao(String umIdTransacao) {
        return repositorioTransacao.buscaTransacaoPorId(umIdTransacao)
                .map(DadosTransacao::new);
    }
}
