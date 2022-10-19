package br.com.pine;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaPagamento;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AlteraItemPago;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.AtualizaCategoria;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.RemoveItemPago;
import br.com.pine.gerenciador.modelo.dominio.transacao.TipoUnidadeMedida;
import br.com.pine.gerenciador.modelo.dominio.transacao.ValorMonetario;

import javax.enterprise.context.ApplicationScoped;
import java.util.Currency;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static br.com.pine.gerenciador.modelo.dominio.transacao.Categoria.ALUGUEL;
import static br.com.pine.gerenciador.modelo.dominio.transacao.Categoria.SALARIO;

@ApplicationScoped
public class Fixtures {
    Random random = new Random();

    public float valorNegativo() {
        return -0.01f;
    }

    public float valorPositivo() {
        return 0.01f;
    }

    public float valorComCasasDecimaisAMais(Currency umaMoeda) {
        var umaQuantidadeIncorreta = umaMoeda.getDefaultFractionDigits() + 1;
        return criaCasasDecimais(umaQuantidadeIncorreta);
    }

    private float criaCasasDecimais(int umaQuantidade) {
        return (float) (IntStream.range(0, umaQuantidade).mapToDouble(num -> num).reduce(1.0f, (a, b) -> a / 10));
    }

    public Currency moedaReal() {
        return Currency.getInstance("BRL");
    }

    public ValorMonetario umValorMonetarioRealPositivo() {
        return new ValorMonetario(moedaReal(), valorPositivo());
    }

    public String umaStringAleatoria() {
        return umaStringAleatoria(random.nextInt(1, 30));
    }

    public String umaStringAleatoria(int tamanhoString) {
        int limiteEsquerda = 'a';
        int limiteDireira = 'z';

        return random.ints(limiteEsquerda, limiteDireira + 1)
                .limit(tamanhoString)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public int umInteiroAleatorio(int umMinimo, int umMaximo) {
        return random.nextInt(umMinimo, umMaximo);
    }

    public int umInteiroAleatorio() {
        return random.nextInt(1, 10);
    }

    public Date umaDataAleatoria() {
        return new Date(random.nextInt() * 1000L);
    }

    public CriaTransacao comandoCriaTransacao() {
        var umComando = new CriaTransacao();
        umComando.descricao = umaStringAleatoria();
        umComando.valor = valorPositivo();
        umComando.nomeDoPagador = umaStringAleatoria();
        umComando.nomeDoRecebedor = umaStringAleatoria();
        umComando.idPagamento = umaStringAleatoria();
        return umComando;
    }

    public AdicionaItemPago comandoAdicionaItemPago(String umIdEntidade) {
        var umComando = new AdicionaItemPago();
        umComando.idTransacao = umIdEntidade;
        umComando.descricao = umaStringAleatoria();
        umComando.quantidade = umInteiroAleatorio();
        umComando.unidadeMedida = TipoUnidadeMedida.UN.name();
        umComando.valorUnidade = valorPositivo();
        return umComando;
    }

    public RemoveItemPago comandoRemoveItemPagoIdentico(AdicionaItemPago umComandoAdiciona) {
        var umComandoRemove = new RemoveItemPago();
        umComandoRemove.idTransacao = umComandoAdiciona.idTransacao;
        umComandoRemove.descricao = umComandoAdiciona.descricao;
        umComandoRemove.quantidade = umComandoAdiciona.quantidade;
        umComandoRemove.unidadeMedida = umComandoAdiciona.unidadeMedida;
        umComandoRemove.valorUnidade = umComandoAdiciona.valorUnidade;
        return umComandoRemove;
    }

    public AlteraItemPago comandoAlteraItemPagoIdentico(AdicionaItemPago umComandoAdiciona) {
        var umComandoAltera = new AlteraItemPago();
        umComandoAltera.idTransacao = umComandoAdiciona.idTransacao;
        umComandoAltera.descricaoAnterior = umComandoAdiciona.descricao;
        umComandoAltera.quantidadeAnterior = umComandoAdiciona.quantidade;
        umComandoAltera.unidadeMedidaAnterior = umComandoAdiciona.unidadeMedida;
        umComandoAltera.valorUnidadeAnterior = umComandoAdiciona.valorUnidade;
        umComandoAltera.descricaoNova = umaStringAleatoria();
        umComandoAltera.quantidadeNova = umInteiroAleatorio();
        umComandoAltera.unidadeMedidaNova = TipoUnidadeMedida.UN.name();
        umComandoAltera.valorUnidadeNova = valorPositivo();
        return umComandoAltera;
    }

    public AdicionaPagamento comandoAdicionaPagamento(String umIdEntidade) {
        var umComando = new AdicionaPagamento();
        umComando.idTransacao = umIdEntidade;
        umComando.idPagamento = umaStringAleatoria();
        return umComando;
    }

    public AtualizaCategoria comandoAtualizaCategoria(String umIdEntidade) {
        var umComando = new AtualizaCategoria();
        umComando.idTransacao = umIdEntidade;
        umComando.conjuntoCategoria = Set.of(SALARIO, ALUGUEL);
        return umComando;
    }
}
