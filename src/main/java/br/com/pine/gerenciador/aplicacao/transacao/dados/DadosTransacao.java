package br.com.pine.gerenciador.aplicacao.transacao.dados;

import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DadosTransacao {
    public String idTransacao;
    public List<DadosItemPago> itens;
    public Set<String> categorias;

    public DadosTransacao(Transacao umaTransacao) {
        this.idTransacao = umaTransacao.id().toString();
        this.itens = umaTransacao.itens()
                .stream().map(DadosItemPago::new)
                .collect(Collectors.toList());
        this.categorias = umaTransacao.categorias()
                .stream().map(Categoria::name)
                .collect(Collectors.toSet());
    }
}
