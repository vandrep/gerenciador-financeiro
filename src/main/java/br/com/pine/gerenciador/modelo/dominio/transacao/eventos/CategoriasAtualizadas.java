package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategorias;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;

import java.time.LocalDateTime;
import java.util.Set;

public class CategoriasAtualizadas implements EventoDeDominio {
    public LocalDateTime ocorridoEm;
    public String idTransacao;
    public Set<String> categorias;

    public CategoriasAtualizadas() {
    }

    public CategoriasAtualizadas(AtualizaCategorias umComando) {
        this.ocorridoEm = LocalDateTime.now();
        this.idTransacao = umComando.idTransacao;
        this.categorias = umComando.categorias;
    }

    @Override
    public String idEntidade() {
        return idTransacao;
    }

    @Override
    public LocalDateTime ocorridoEm() {
        return ocorridoEm;
    }

    @Override
    public int versaoDoEvento() {
        return 1;
    }
}
