package br.com.pine.gerenciador.modelo.dominio.transacao;

import br.com.pine.Fixtures;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static br.com.pine.gerenciador.modelo.dominio.MensagensErro.TIPO_UNIDADE_DE_MEDIDA_MULTIPLICADOR_FORA_DO_INTERVALO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class QuantidadeTest {
    @Inject
    Fixtures fixtures;
    Quantidade quantidade;
    int multiplicadorInteiro;

    @BeforeEach
    void setUp() {
        multiplicadorInteiro = fixtures.umInteiroAleatorio(1, 30);
    }

    @Test
    void criaUmaQuantidadeDeUnidadeComSucesso() {
        quantidade = Quantidade.deTipoUnidadeMedida("UN", multiplicadorInteiro);

        assertEquals(multiplicadorInteiro, quantidade.multiplicador().intValue());
        assertEquals(TipoUnidadeMedida.UN, quantidade.tipoUnidadeDeMedida());
    }

    @Test
    void criaUmaQuantidadeDeUnidadeMenorQueUmComErro() {

        var erro = assertThrows(IllegalArgumentException.class,
                () -> quantidade = Quantidade.deTipoUnidadeMedida("UN", 0));

        assertEquals(TIPO_UNIDADE_DE_MEDIDA_MULTIPLICADOR_FORA_DO_INTERVALO.mensagem, erro.getMessage());
    }
}