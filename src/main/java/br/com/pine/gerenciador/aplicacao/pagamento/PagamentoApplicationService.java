package br.com.pine.gerenciador.aplicacao.pagamento;

import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Pagamento;
import br.com.pine.gerenciador.portas.adaptadores.EventoArmazenado;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoApplicationService {
    private final String PAGAMENTO = "Pagamento";

    public Uni<Void> criaPagamento(CriaPagamentoEmReal umComando) {
        var pagamento = new Pagamento();
        var evento = pagamento.processa(umComando);
        pagamento.aplica(evento);
        return armazena(evento, pagamento.getIdEntidade());
    }

    public Uni<Void> adicionaItemPago(AdicionaItemPago umComando) {
        return instanciaPagamento(umComando.idPagamento)
                .map(pagamento -> aplicaAlteracao(pagamento, umComando))
                .chain(evento -> armazena(evento, umComando.idPagamento));
    }

    private Uni<Pagamento> instanciaPagamento(String umIdPagamento) {
        return Uni.createFrom().item(new Pagamento())
                .onItem().call(pagamento -> EventoArmazenado.eventosDoId(umIdPagamento)
                        .onCompletion().ifEmpty().failWith(new NotFoundException("Sem eventos"))
                        .map(this::converteEvento)
                        .invoke(pagamento::aplicaGenerico)
                        .collect().asList());
    }

    private EventoDominio converteEvento(EventoArmazenado eventoArmazenado) {
        return eventoArmazenado.getEventoDominio();
    }

    private EventoDominio aplicaAlteracao(Pagamento pagamento, AdicionaItemPago umComando) {
        var eventoDominio = pagamento.processa(umComando);
        pagamento.aplica(eventoDominio);
        return eventoDominio;
    }

    private Uni<Void> armazena(EventoDominio umEvento, String umIdEntidade) {
        return EventoArmazenado.armazenaEvento(
                PAGAMENTO,
                umEvento.getClass().getSimpleName(),
                umIdEntidade,
                umEvento);
    }
}
