package br.com.pine.gerenciador.portas.adaptadores.entrada;

import br.com.pine.gerenciador.aplicacao.transacao.TransacaoApplicationService;
import br.com.pine.gerenciador.aplicacao.transacao.TransacaoQueryApplicationService;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItem;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.dados.DadosTransacao;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transacao")
public class AdaptadorHTTPTransacao {
    @Inject
    TransacaoApplicationService transacaoApplicationService;
    @Inject
    TransacaoQueryApplicationService transacaoQueryApplicationService;

    @POST
    @Path("/cria")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> cria(CriaTransacao umComando) {
        return transacaoApplicationService.processa(umComando);
    }

    @POST
    @ReactiveTransactional
    @Path("/adicionaItemPago")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> adicionaItemPago(AdicionaItem umComando) {
        return transacaoApplicationService.processa(umComando);
    }
//
//    @POST
//    @Path("/{idTransacao}/atualiza")
//    @ReactiveTransactional
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Uni<Void> atualiza(@RestPath String idTransacao,
//                              CriaTransacao umComando) {
//        return transacaoApplicationService.criaTransacaoNova(umComando);
//    }

    @GET
    @Path("/consulta/{idTransacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DadosTransacao> consulta(@RestPath String idTransacao) {
        return transacaoQueryApplicationService.consultaTransacao(idTransacao);
    }
}
