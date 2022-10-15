package br.com.pine.gerenciador.aplicacao.pagamento;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Date;

public class CriaPagamentoEmReal extends Comando {
    public Date data;
    public float valor;
    public String nomeFornecedor;
    public String nomeBeneficiario;
}
