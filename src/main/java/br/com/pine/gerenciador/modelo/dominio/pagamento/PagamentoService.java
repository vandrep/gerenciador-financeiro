package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.pagamento.CriaPagamentoEmReal;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PagamentoService {

    public EventoDominio criaPagamentoNovo(CriaPagamentoEmReal umComando) {
        var pagamento = new Pagamento();
        pagamento.setIdEntidade(UUID.randomUUID().toString());
        return pagamento.processa(umComando);
    }

    public Pagamento instanciaPagamento(List<EventoDominio> listaEvento) {
        var pagamento = new Pagamento();
        pagamento.setIdEntidade(listaEvento.get(0).getIdEntidade());
        for (EventoDominio eventoDominio : listaEvento) {
            pagamento.aplicaEvento(eventoDominio);
        }
        return pagamento;
    }
}
