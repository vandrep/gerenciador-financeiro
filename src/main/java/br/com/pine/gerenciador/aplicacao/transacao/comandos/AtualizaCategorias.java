package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Set;

public class AtualizaCategorias implements Comando {
    public String idTransacao;
    public Set<String> categorias;
}
