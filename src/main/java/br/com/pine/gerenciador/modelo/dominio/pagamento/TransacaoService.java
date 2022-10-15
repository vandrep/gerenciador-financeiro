package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.transacao.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransacaoService {

    public EventoDominio criaTransacao(CriaTransacao umComando) {
        var pagamento = new Transacao(UUID.randomUUID().toString());
        return pagamento.processa(umComando);
    }

    public Transacao instanciaTransacao(List<EventoDominio> listaEvento) {
        var pagamento = new Transacao(listaEvento.get(0).getIdEntidade());
        for (EventoDominio eventoDominio : listaEvento) {
            pagamento.aplicaEvento(eventoDominio);
        }
        return pagamento;
    }
}
