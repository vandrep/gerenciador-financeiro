package br.com.pine.gerenciador.aplicacao.transacao.comandos;

import br.com.pine.gerenciador.modelo.dominio.Comando;

import java.util.Set;

public class CriaTransacao implements Comando {
    public String pagamento;
    public Set<String> categorias;
}
