package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.aplicacao.pagamento.dados.DadosParcela;
import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Set;

public class CriaPagamento implements Comando {
    public String idConta;
    public String descricao;
    public String nomeDoPagador;
    public String nomeDoRecebedor;
    public Set<DadosParcela> parcelas;
}
