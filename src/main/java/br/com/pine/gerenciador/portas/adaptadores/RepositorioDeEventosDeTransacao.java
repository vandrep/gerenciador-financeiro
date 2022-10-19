package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.transacao.RepositorioTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import io.smallrye.mutiny.GroupedMulti;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class RepositorioDeEventosDeTransacao implements RepositorioTransacao {

    public Multi<Transacao> buscaTodasTransacoes() {
        return stream("tipoentidade", Transacao.class.getSimpleName())
                .stage(this::validaMultiVazia)
                .stage(this::converteParaEventoDeDominio)
                .stage(this::agrupaEventosPorId)
                .onItem().transformToUniAndConcatenate(this::instanciaTransacao);
    }

    public Uni<Transacao> buscaTransacaoPorId(String umIdEntidade) {
        return stream("identidade = ?1 and tipoentidade = ?2", umIdEntidade, Transacao.class.getSimpleName())
                .stage(this::validaMultiVazia)
                .stage(this::converteParaEventoDeDominio)
                .stage(this::instanciaTransacao);
    }

    public Uni<Void> armazenaNovosEventosDaTransacao(Multi<EventoDeDominio> multiEventoDeDominio) {
        return multiEventoDeDominio
                .map(this::converteParaEventoArmazenado)
                .onItem().call(this::persist)
                .collect().asList()
                .replaceWithVoid();
    }

    private EventoArmazenado converteParaEventoArmazenado(EventoDeDominio umEventoDeDominio) {
        return new EventoArmazenado(umEventoDeDominio, Transacao.class.getSimpleName());
    }

    private Multi<EventoArmazenado> validaMultiVazia(Multi<EventoArmazenado> multiEventoArmazenado) {
        return multiEventoArmazenado.onCompletion()
                .ifEmpty().failWith(new NotFoundException("Sem eventos"));
    }

    private Multi<EventoDeDominio> converteParaEventoDeDominio(Multi<EventoArmazenado> multiEventosArmazenados) {
        return multiEventosArmazenados.map(EventoArmazenado::getEventoDominio);
    }

    private Multi<GroupedMulti<String, EventoDeDominio>> agrupaEventosPorId(Multi<EventoDeDominio> multiEventosDeDominio) {
        return multiEventosDeDominio.group().by(EventoDeDominio::getIdTransacao);
    }

    private Uni<Transacao> instanciaTransacao(GroupedMulti<String, EventoDeDominio> multiGrupoEventoDeDominio) {
        return multiGrupoEventoDeDominio.collect().in(Transacao::new, RaizAgregado::aplicaEvento);
    }

    public Uni<Transacao> instanciaTransacao(Multi<EventoDeDominio> multiEventosDeDominio) {
        return multiEventosDeDominio.collect().in(Transacao::new, RaizAgregado::aplicaEvento);
    }
}
