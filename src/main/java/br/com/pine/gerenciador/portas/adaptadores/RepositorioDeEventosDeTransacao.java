package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
import br.com.pine.gerenciador.modelo.dominio.transacao.RepositorioTransacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoDeDominioTransacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.smallrye.mutiny.GroupedMulti;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.text.SimpleDateFormat;

@ApplicationScoped
public class RepositorioDeEventosDeTransacao implements RepositorioTransacao {
    private static final ObjectMapper json = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .defaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"))
            .build();

    public Multi<Transacao> buscaTodasTransacoes() {
        return stream("tipoentidade", Transacao.class.getSimpleName())
                .stage(this::validaMultiVazia)
                .stage(this::converteParaEventoDeDominioTransacao)
                .stage(this::agrupaEventosPorId)
                .onItem().transformToUniAndConcatenate(this::instanciaTransacao);
    }

    public Uni<Transacao> buscaTransacaoPorId(String umIdEntidade) {
        return stream("identidade = ?1 and tipoentidade = ?2", umIdEntidade, Transacao.class.getSimpleName())
                .stage(this::validaMultiVazia)
                .stage(this::converteParaEventoDeDominioTransacao)
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

    private Multi<EventoDeDominioTransacao> converteParaEventoDeDominioTransacao(Multi<EventoArmazenado> multiEventosArmazenados) {
        return multiEventosArmazenados
                .map(this::deserializa);
    }

    private EventoDeDominioTransacao deserializa(EventoArmazenado umEventoArmazenado) {
        var classeDoEvento = EventoDeDominioTransacao.class.getPackageName() + "." + umEventoArmazenado.tipoEvento;
        try {
            return (EventoDeDominioTransacao) json.readValue(umEventoArmazenado.getEventoDominio(), Class.forName(classeDoEvento));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Multi<GroupedMulti<String, EventoDeDominioTransacao>> agrupaEventosPorId(Multi<EventoDeDominioTransacao> multiEventosDeDominio) {
        return multiEventosDeDominio.group().by(EventoDeDominio::idEntidade);
    }

    private Uni<Transacao> instanciaTransacao(GroupedMulti<String, EventoDeDominioTransacao> multiGrupoEventoDeDominio) {
        return multiGrupoEventoDeDominio.collect().in(Transacao::new, RaizAgregado::aplicaEvento);
    }

    private Uni<Transacao> instanciaTransacao(Multi<EventoDeDominioTransacao> multiEventosDeDominio) {
        return multiEventosDeDominio.collect().in(Transacao::new, RaizAgregado::aplicaEvento);
    }
}
