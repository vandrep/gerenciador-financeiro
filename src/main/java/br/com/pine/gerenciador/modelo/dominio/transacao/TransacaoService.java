package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoDeDominioTransacao;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransacaoService {

    public Multi<EventoDeDominio> criaTransacao(CriaTransacao umComando) {
        var transacao = new Transacao();
        return transacao.processaComando(umComando);
    }

    public Uni<Transacao> instanciaTransacao(Multi<EventoDeDominioTransacao> multiEventoDeDominio) {
        return multiEventoDeDominio.collect().in(Transacao::new, RaizAgregado::aplicaEvento);
    }
}
