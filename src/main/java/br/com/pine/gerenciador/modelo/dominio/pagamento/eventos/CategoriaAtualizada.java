package br.com.pine.gerenciador.modelo.dominio.pagamento.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.transacao.AdicionaItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.pagamento.AtualizaCategoria;
import br.com.pine.gerenciador.modelo.dominio.pagamento.Categoria;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdTransacao;
import br.com.pine.gerenciador.modelo.dominio.pagamento.UnidadeMedida;

import java.time.LocalDateTime;
import java.util.Set;

public class CategoriaAtualizada extends EventoDeDominio {
    public IdTransacao idTransacao;
    public LocalDateTime ocorridoEm;
    public Set<Categoria> conjuntoCategoria;

    public CategoriaAtualizada(){
    }

    public CategoriaAtualizada(AtualizaCategoria umComando) {
        this.idTransacao = new IdTransacao(umComando.idTransacao);
        this.ocorridoEm = LocalDateTime.now();
        this.conjuntoCategoria = umComando.conjuntoCategoria;
    }

    @Override
    public String getIdEntidade() {
        return idTransacao.id();
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
