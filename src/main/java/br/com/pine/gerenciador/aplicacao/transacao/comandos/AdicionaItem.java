package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Set;

public class AdicionaItem implements Comando {
    public String idTransacao;
    public String descricao;
    public float quantidade;
    public String tipoUnidadeMedida;
    public float valorUnitario;
    public Set<String> categorias;
}
