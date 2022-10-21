package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategorias;
import br.com.pine.gerenciador.modelo.dominio.Comando;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;

import java.time.LocalDateTime;
import java.util.Set;

public class CategoriasAtualizadas implements EventoDeDominio {
    private LocalDateTime ocorridoEm;
    private int versao;
    private AtualizaCategorias comando;

    public CategoriasAtualizadas() {
    }

    public CategoriasAtualizadas(AtualizaCategorias umComando) {
        ocorridoEm = LocalDateTime.now();
        versao = 1;
        comando = umComando;
    }

    @Override
    public String idEntidade() {
        return comando.idTransacao;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int getVersao() {
        return versao;
    }

    @Override
    public Comando getComando() {
        return this.comando;
    }

    public Set<String> categorias(){
        return comando.categorias;
    }
}
