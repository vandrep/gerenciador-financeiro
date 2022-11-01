//package br.com.pine.gerenciador.modelo.dominio.pagamento;
//
//import br.com.pine.gerenciador.modelo.dominio.Comando;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//
//public class PagamentoCriado implements EventoDeDominioPagamento {
//    private LocalDateTime ocorridoEm;
//    private int versao;
//    private String idPagamento;
//    private CriaPagamento comando;
//
//    public PagamentoCriado() {
//    }
//
//    public PagamentoCriado(CriaPagamento umComando,
//                           String umIdPagamento) {
//        ocorridoEm = LocalDateTime.now();
//        versao = 1;
//        idPagamento = umIdPagamento;
//        comando = umComando;
//    }
//
//
//    public String idEntidade() {
//        return idPagamento;
//    }
//
////    @Override
////    public LocalDateTime getOcorridoEm() {
////        return ocorridoEm;
////    }
////
////    @Override
////    public int getVersao() {
////        return versao;
////    }
////
////    public String getIdPagamento() {
////        return idEntidade();
////    }
//
//
//    public Comando getComando() {
//        return comando;
//    }
//
//    public String idConta() {
//        return comando.idConta;
//    }
//
//    public String descricao() {
//        return comando.descricao;
//    }
//
//    public String nomeDoPagador() {
//        return comando.nomeDoPagador;
//    }
//
//    public String nomeDoRecebedor() {
//        return comando.nomeDoRecebedor;
//    }
//}
