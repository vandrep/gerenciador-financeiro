package br.com.pine.gerenciador.modelo.dominio.pagamento;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Set;

public class AtualizaCategoria extends Comando {
    public String idTransacao;
    public Set<Categoria> conjuntoCategoria;
}
