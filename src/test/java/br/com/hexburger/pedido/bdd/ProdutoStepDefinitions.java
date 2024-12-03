package br.com.hexburger.pedido.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class ProdutoStepDefinitions {

    private static final String ENDPOINT_PRODUTOS = "http://localhost:8080/v1/produto";

    private Response response;
    private String payload;
    private String produtoId;

    @Dado("o payload de produto válido")
    public void oPayloadDeProdutoValido() {
        payload = "{\"nome\": \"Hex Burger API\", \"descricao\": \"Pão e Hambuguer no formato hexagonal para teste API\", \"valor\": 20, \"categoria\": \"LANCHE\"}";
    }

    @Dado("o payload de produto com valor inválido")
    public void oPayloadDeProdutoComValorInvalido() {
        payload = "{\"nome\": \"Hex Burger API\", \"descricao\": \"Pão e Hambuguer no formato hexagonal para teste API\", \"valor\": 0, \"categoria\": \"LANCHE\"}";
    }

    @Dado("o payload de produto já existente")
    public void oPayloadDeProdutoJaExistente() {
        payload = "{\"nome\": \"Hex Burger Para Editar 2\", \"descricao\": \"Pão e dois Hambugueres no formato hexagonal\", \"valor\": 27, \"categoria\": \"LANCHE\"}";
    }

    @Dado("o produto já existe no sistema")
    public void oProdutoJaExisteNoSistema() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_PRODUTOS)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Dado("o payload de produto válido para edição")
    public void oPayloadDeProdutoValidoParaEdicao() {
        payload = "{\"nome\": \"Hex Burger Nova Edição\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 200, \"categoria\": \"LANCHE\"}";
    }

    @Dado("o payload de produto com valor inválido para edição")
    public void oPayloadDeProdutoComValorInvalidoParaEdicao() {
        payload = "{\"nome\": \"Hex Burger Nova Edição\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 0, \"categoria\": \"LANCHE\"}";
    }

    @Dado("o produto existe no sistema com ID {string}")
    public void oProdutoExisteNoSistemaComId(String id) {
        produtoId = id;
    }

    @Dado("o produto não existe no sistema com ID {string}")
    public void oProdutoNaoExisteNoSistemaComId(String id) {
        produtoId = id;
    }

    @Dado("a categoria {string} existe no sistema")
    public void aCategoriaExisteNoSistema(String categoria) {
        payload = "{\"nome\": \"Hex Burger API\", \"descricao\": \"Pão e Hambuguer no formato hexagonal\", \"valor\": 20, \"categoria\": \"" + categoria + "\"}";
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_PRODUTOS)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Dado("a categoria {string} não existe no sistema")
    public void aCategoriaNaoExisteNoSistema() {
        // Nenhuma ação necessária, apenas simula que a categoria não existe.
    }

    @Quando("o produto for criado")
    public void oProdutoForCriado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_PRODUTOS);
    }

    @Quando("o produto for editado")
    public void oProdutoForEditado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .put(ENDPOINT_PRODUTOS + "/{id}", produtoId);
    }

    @Quando("o produto for removido")
    public void oProdutoForRemovido() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_PRODUTOS + "/{id}", produtoId);
    }

    @Quando("os produtos forem buscados pela categoria")
    public void osProdutosForemBuscadosPelaCategoria() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_PRODUTOS + "/{categoria}", "LANCHE");
    }

    @Então("a resposta deve ter o status produto {int} OK")
    public void aRespostaProdutoDeveTerOStatusOk() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Então("a resposta deve ter o status {int} produto Bad Request")
    public void aRespostaDeveTerOStatusBadRequest() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Então("a resposta deve ter o status {int} Conflict")
    public void aRespostaDeveTerOStatusConflict() {
        response.then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Então("a resposta deve ter o status {int} produto Not Found")
    public void aRespostaProdutoDeveTerOStatusNotFound() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
