package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.aplicacao.transacao.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.TransacaoApplicationService;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Transacao;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pagamento")
public class TransacaoWeb {
    @Inject
    TransacaoApplicationService transacaoApplicationService;

    @POST
    @Path("/cria")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> criaPagamento(CriaTransacao umComando) {
        return transacaoApplicationService.criaPagamento(umComando);
    }

    @POST
    @Path("/adicionaItemPago")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> adicionaItemPago(AdicionaItemPago umComando) {
        return transacaoApplicationService.adicionaItemPago(umComando);
    }

    @GET
    @Path("/listaTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<EventoDominio> listaTodos(){
        return transacaoApplicationService.listaTodos();
    }

    @GET
    @Path("/listaPagamentos")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Transacao> listaPagamentos(){
        return transacaoApplicationService.listaPagamentos();
    }
}
