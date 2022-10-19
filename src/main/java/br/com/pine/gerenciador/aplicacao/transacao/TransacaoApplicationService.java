package br.com.pine.gerenciador.aplicacao.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.RepositorioTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.TransacaoService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TransacaoApplicationService {
    @Inject
    RepositorioTransacao repositorioTransacao;
    @Inject
    TransacaoService transacaoService;

    public Uni<Void> criaTransacaoNova(CriaTransacao umComando) {
        return repositorioTransacao.armazenaNovosEventosDaTransacao(transacaoService.criaTransacao(umComando));
    }

    public Uni<Void> adicionaItemPagoEmUmaTransacao(AdicionaItemPago umComando) {
        return transacao(umComando.idTransacao)
                .onItem().transform(transacao -> transacao.processaComando(umComando))
                .onItem().transformToUni(itemPagoAdicionado ->
                        repositorioTransacao.armazenaNovosEventosDaTransacao(itemPagoAdicionado));
    }

    private Uni<Transacao> transacao(String umIdTransacao) {
        return repositorioTransacao.buscaTransacaoPorId(umIdTransacao);
    }

}
