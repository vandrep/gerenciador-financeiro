package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.TransacaoService;
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
    private final String NOME_ENTIDADE = Transacao.class.getSimpleName();

    public Uni<Void> cria(CriaTransacao umComando) {
        return repositorioEvento.armazena(transacaoService.criaTransacao(umComando), NOME_ENTIDADE);
    }

    public Uni<Void> adicionaItemPago(AdicionaItemPago umComando) {
        return repositorioEvento.ListaEventoDeDominioDoIdEntidade(umComando.idTransacao)
                .onItem().transform(transacaoService::instanciaTransacao)
                .onItem().transform(transacao -> transacao.processaComando(umComando))
                .onItem().transformToUni(itemPagoAdicionado -> repositorioEvento.armazena(itemPagoAdicionado, NOME_ENTIDADE));
    }

    public Multi<Transacao> lista() {
        return repositorioEvento.listaEventoDeDominioAgrupadoPorIdEntidade()
                .map(lista -> transacaoService.instanciaTransacao(lista));
    }

    public Uni<Transacao> consultaTransacao(String idTransacao) {
        return repositorioEvento.ListaEventoDeDominioDoIdEntidade(idTransacao)
                .onItem().transform(transacaoService::instanciaTransacao);
    }
}
