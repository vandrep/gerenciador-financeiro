package br.com.pine.gerenciador.aplicacao.transacao.dados;

import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.Transacao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DadosTransacao {
    public String idTransacao;
    public float valor;
    public String nomeDoPagador;
    public String nomeDoRecebedor;
    public List<DadosItemPago> listaDeItemPago;
    public Set<String> conjuntoDeCategoria;

    public DadosTransacao(Transacao umaTransacao) {
        this.idTransacao = umaTransacao.idTransacao().id();
        this.valor = umaTransacao.valor();
        this.nomeDoPagador = umaTransacao.nomeDoPagador();
        this.nomeDoRecebedor = umaTransacao.nomeDoRecebedor();
        this.listaDeItemPago = umaTransacao.listaItemPago()
                .stream().map(DadosItemPago::new)
                .collect(Collectors.toList());
        this.conjuntoDeCategoria = umaTransacao.conjuntoCategoria()
                .stream().map(Categoria::name)
                .collect(Collectors.toSet());
    }
}
