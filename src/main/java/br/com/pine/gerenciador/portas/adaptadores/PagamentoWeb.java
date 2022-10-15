package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.aplicacao.pagamento.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.pagamento.CriaPagamentoEmReal;
import br.com.pine.gerenciador.aplicacao.pagamento.PagamentoApplicationService;
import br.com.pine.gerenciador.modelo.dominio.EventoDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Pagamento;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.GroupedMulti;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pagamento")
public class PagamentoWeb {
    @Inject
    PagamentoApplicationService pagamentoApplicationService;

    @POST
    @Path("/cria")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> criaPagamento(CriaPagamentoEmReal umComando) {
        return pagamentoApplicationService.criaPagamento(umComando);
    }

    @POST
    @Path("/adicionaItemPago")
    @ReactiveTransactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> adicionaItemPago(AdicionaItemPago umComando) {
        return pagamentoApplicationService.adicionaItemPago(umComando);
    }

    @GET
    @Path("/listaTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<EventoDominio> listaTodos(){
        return pagamentoApplicationService.listaTodos();
    }

    @GET
    @Path("/listaPagamentos")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Pagamento> listaPagamentos(){
        return pagamentoApplicationService.listaPagamentos();
    }
}
