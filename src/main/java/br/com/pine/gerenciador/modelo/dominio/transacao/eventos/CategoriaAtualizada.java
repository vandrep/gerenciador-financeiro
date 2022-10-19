package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategoria;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;

import java.time.LocalDateTime;
import java.util.Set;

public class CategoriaAtualizada extends EventoDeDominio {
    public String idEntidade;
    public LocalDateTime ocorridoEm;
    public Set<Categoria> conjuntoCategoria;

    public CategoriaAtualizada() {
    }

    public CategoriaAtualizada(AtualizaCategoria umComando) {
        this.idEntidade = umComando.idTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.conjuntoCategoria = umComando.conjuntoCategoria;
    }

    @Override
    public String getIdTransacao() {
        return idEntidade;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
