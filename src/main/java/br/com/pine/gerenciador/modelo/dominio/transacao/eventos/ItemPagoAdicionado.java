package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.modelo.dominio.EventoDeDominio;
import br.com.pine.gerenciador.modelo.dominio.transacao.UnidadeMedida;

import java.time.LocalDateTime;

public class ItemPagoAdicionado extends EventoDeDominio {
    public String idEntidade;
    public LocalDateTime ocorridoEm;
    public String descricao;
    public int quantidade;
    public UnidadeMedida unidadeMedida;
    public float valorUnidade;

    public ItemPagoAdicionado() {
    }

    public ItemPagoAdicionado(AdicionaItemPago umComando) {
        this.idEntidade = umComando.idTransacao;
        this.ocorridoEm = LocalDateTime.now();
        this.descricao = umComando.descricao;
        this.unidadeMedida = UnidadeMedida.valueOf(umComando.unidadeMedida);
        this.valorUnidade = umComando.valorUnidade;
        this.quantidade = umComando.quantidade;
    }

    @Override
    public String getIdEntidade() {
        return idEntidade;
    }

    @Override
    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
