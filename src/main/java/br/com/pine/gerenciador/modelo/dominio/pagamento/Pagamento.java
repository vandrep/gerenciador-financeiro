//package br.com.pine.gerenciador.modelo.dominio.pagamento;
//
//import br.com.pine.gerenciador.aplicacao.pagamento.dados.DadosParcela;
//import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
//import br.com.pine.gerenciador.modelo.dominio.Evento;
//import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
//import br.com.pine.gerenciador.modelo.dominio.RaizAgregado;
//import br.com.pine.gerenciador.modelo.dominio.conta.IdConta;
//import br.com.pine.gerenciador.modelo.dominio.transacao.IdTransacao;
//import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;
//import br.com.pine.gerenciador.modelo.dominio.transacao.ValorMonetario;
//import br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventoTransacao;
//import io.smallrye.mutiny.Multi;
//
//import java.util.Set;
//
//public class Pagamento extends RaizAgregado {
//    private IdPagamento idPagamento;
//    private String descricao;
//    private String nomeDoPagador;
//    private String nomeDoRecebedor;
//    private IdTransacao idTransacao;
//    private Set<IdParcela> idParcelas;
//
//    private Multi<EventoDeDominio> processa(CriaPagamento umComando) {
////        validaComando;
//        var umidPagamento = new IdPagamento();
//        var umidTransacao = new IdTransacao();
//
//        for (DadosParcela parcela : umComando.parcelas) {
//            new Parcela(
//                    parcela.dataPagamento,
//                    ValorMonetario.emReal(parcela.valor),
//                    parcela.estadoPagamento,
//                    idPagamento,
//                    new IdConta(umComando.idConta));
//        }
//        var eventoPagamentoCriado = eventoPagamentoCriado(umComando);
//        var eventoTransacaoCriada = eventoTransacaoCriada(umComando);
//
//        return Multi.createBy().concatenating().streams(
//                eventoPagamentoCriado,
//                eventoTransacaoCriada);
//    }
//
//    private Multi<EventoDeDominio> eventoPagamentoCriado(CriaPagamento umComando) {
//        return Multi.createFrom().items(
//                new PagamentoCriado(
//                        umComando,
//                        idPagamento.id()));
//    }
//
//    private Multi<EventoDeDominio> eventoTransacaoCriada(CriaPagamento umComando) {
//        var comandoCriaTransacao = new CriaTransacao();
//        comandoCriaTransacao.id = idTransacao.id();
//        comandoCriaTransacao.descricao = umComando.descricao;
//        comandoCriaTransacao.nomeDoPagador = umComando.nomeDoPagador;
//        comandoCriaTransacao.nomeDoRecebedor = umComando.nomeDoRecebedor;
//        comandoCriaTransacao.idPagamento = idPagamento.id();
//        var transacao = new Transacao();
//        return transacao.processaComando(comandoCriaTransacao);
//    }
//
//    private Pagamento(IdPagamento umIdPagamento,
//                      IdTransacao umIdTransacao,
//                      Set<IdParcela> unsIdParcelas) {
//        idPagamento = umIdPagamento;
//        idTransacao = umIdTransacao;
//        idParcelas = unsIdParcelas;
//    }
//
//    @Override
//    protected void mutate(Evento evento) {
//
//    }
//}
