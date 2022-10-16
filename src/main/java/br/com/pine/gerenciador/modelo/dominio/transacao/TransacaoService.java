package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransacaoService {

    public List<EventoDeDominio> criaTransacao(CriaTransacao umComando) {
        var transacao = new Transacao(UUID.randomUUID().toString());
        return transacao.processaComando(umComando);
    }

    public Transacao instanciaTransacao(List<EventoDeDominio> listaEvento) {
        var transacao = new Transacao(listaEvento.get(0).getIdEntidade());
        listaEvento.forEach(transacao::aplicaEvento);
        return transacao;
    }

}
