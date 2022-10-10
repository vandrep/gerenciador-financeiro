package br.com.pine.gerenciador;

import br.com.pine.gerenciador.modelo.dominio.pagamento.ValorMonetario;

import javax.enterprise.context.ApplicationScoped;
import java.util.Currency;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

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

    public ValorMonetario umValorMonetarioRealPositivo(){
        return new ValorMonetario(moedaReal(), valorPositivo());
    }

    public String umaStringAleatoria(){
        return umaStringAleatoria(random.nextInt(1, 30));
    }
    public String umaStringAleatoria(int tamanhoString){
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

    public Date umaDataAleatoria() {
        return new Date(random.nextInt() * 1000L);
    }
}
