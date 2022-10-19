package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.portas.adaptadores.EventoArmazenado;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface RepositorioTransacao extends PanacheRepository<EventoArmazenado> {
    Multi<Transacao> buscaTodasTransacoes();
    Uni<Transacao> buscaTransacaoPorId(String umIdEntidade);
    Uni<Void> armazenaNovosEventosDaTransacao(Multi<EventoDeDominio> listaEventoDeDominio);
}
