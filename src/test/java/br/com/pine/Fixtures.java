package br.com.pine;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItem;
import br.com.pine.gerenciador.aplicacao.transacao.comandos.CriaTransacao;
import br.com.pine.gerenciador.modelo.dominio.pagamento.IdPagamento;
import br.com.pine.gerenciador.modelo.dominio.transacao.Categoria;
import br.com.pine.gerenciador.modelo.dominio.transacao.TipoUnidadeMedida;
import br.com.pine.gerenciador.modelo.dominio.transacao.ValorMonetario;

import javax.enterprise.context.ApplicationScoped;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class Fixtures {
    Random random = new Random();

    public float floatNegativo() {
        return -0.01f;
    }

    public float floatPositivo() {
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
        return ValorMonetario.emReal(floatPositivo());
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
        umComando.pagamento = umaStringAleatoria();
        umComando.categorias = this.categoriasString();
        return umComando;
    }

    public Set<String> categoriasString(){
        var categorias = new HashSet<String>();
        categorias.add("REEMBOLSO");
        categorias.add("BAR");
        return categorias;
    }

    public Set<Categoria> categorias(){
        return this.categoriasString()
                .stream().map(Categoria::valueOf)
                .collect(Collectors.toSet());
    }

    public AdicionaItem comandoAdicionaItemPago(String umIdTransacao) {
        var umComando = new AdicionaItem();
        umComando.idTransacao = umIdTransacao;
        umComando.descricao = umaStringAleatoria();
        umComando.quantidade = umInteiroAleatorio();
        umComando.tipoUnidadeMedida = TipoUnidadeMedida.UN.name();
        umComando.valorUnitario = floatPositivo();
        return umComando;
    }

    public IdPagamento umIdPagamento() {
        return new IdPagamento();
    }

//    public RemoveItemPago comandoRemoveItemPagoIdentico(AdicionaItemPago umComandoAdiciona) {
//        var umComandoRemove = new RemoveItemPago();
//        umComandoRemove.idTransacao = umComandoAdiciona.idTransacao;
//        umComandoRemove.descricao = umComandoAdiciona.descricao;
////        umComandoRemove.quantidade = umComandoAdiciona.quantidade;
//        umComandoRemove.tipoUnidadeMedida = umComandoAdiciona.tipoUnidadeMedida;
//        umComandoRemove.valorUnidade = umComandoAdiciona.valorUnitario;
//        return umComandoRemove;
//    }

//    public AlteraItemPago comandoAlteraItemPagoIdentico(AdicionaItemPago umComandoAdiciona) {
//        var umComandoAltera = new AlteraItemPago();
//        umComandoAltera.idTransacao = umComandoAdiciona.idTransacao;
//        umComandoAltera.descricaoAnterior = umComandoAdiciona.descricao;
////        umComandoAltera.quantidadeAnterior = umComandoAdiciona.quantidade;
//        umComandoAltera.unidadeMedidaAnterior = umComandoAdiciona.tipoUnidadeMedida;
//        umComandoAltera.valorUnidadeAnterior = umComandoAdiciona.valorUnitario;
//        umComandoAltera.descricaoNova = umaStringAleatoria();
//        umComandoAltera.quantidadeNova = umInteiroAleatorio();
//        umComandoAltera.unidadeMedidaNova = TipoUnidadeMedida.UN.name();
//        umComandoAltera.valorUnidadeNova = valorPositivo();
//        return umComandoAltera;
//    }

//    public AtualizaCategorias comandoAtualizaCategoria(String umIdEntidade) {
//        var umComando = new AtualizaCategorias();
//        umComando.idTransacao = umIdEntidade;
//        umComando.categorias = Set.of("SALARIO", "ALUGUEL");
//        return umComando;
//    }

//    public Quantidade umaQuantidade(){
//        return new Quantidade();
//    }

//    public ValorItem umValorItem(){
//        return new ValorItem(
//                umaQuantidade(),
//                umValorMonetarioRealPositivo()
//        );
//    }
}
