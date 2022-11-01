package br.com.pine.gerenciador.portas.adaptadores;

import br.com.pine.gerenciador.aplicacao.transacao.comandos.AdicionaItem;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static br.com.pine.gerenciador.modelo.dominio.transacao.TipoUnidadeMedida.UN;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@QuarkusTest
class AdaptadorHTTPTransacaoTest {

    @BeforeEach
    void setUp() {
    }

    @TestHTTPResource("/transacao/cria")
    URL urlCria;

    @Test
    void chamaEndPointComComandoNuloComErro() {
        given().contentType(JSON)
                .when().post(urlCria)
                .then()
                .statusCode(500);
    }

    @TestHTTPResource("/transacao/adicionaItemPago")
    URL urlAdiciona;

    @Test
    void chamaEndPointComComandoReferenciandoTransacaoInexistenteComErro() {
        var umComando = new AdicionaItem();
        umComando.idTransacao = "bogus";
        umComando.descricao = "OK";
        umComando.quantidade = 5;
        umComando.tipoUnidadeMedida = UN.name();
        umComando.valorUnitario = 50;
        given().contentType(JSON).body(umComando)
                .when().post(urlAdiciona)
                .then()
                .statusCode(404);
    }
}