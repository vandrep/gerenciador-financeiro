package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;

import java.util.Set;

public class AtualizaCategoria extends Comando {
    public String idTransacao;
    public Set<Categoria> conjuntoCategoria;
}
