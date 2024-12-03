package br.com.hexburger.pedido.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class PedidoStepDefinitions {

    private static final String ENDPOINT_PEDIDOS = "http://localhost:8080/v1/pedido";

    private Response response;
    private String payload;

    @Dado("o payload de pedido válido")
    public void oPayloadDePedidoValido() {
        payload = """
            {
                "combos": [
                    {
                        "produtos": [
                            {"id": "f55f733e-aeb3-4955-87d7-b521895cae80"}
                        ]
                    }
                ],
                "cliente": {
                    "cpf": "98765432108",
                    "nome": "Fernando",
                    "email": "fernando@email.com"
                }
            }
        """;
    }

    @Dado("o payload de pedido com combo vazio")
    public void oPayloadDePedidoComComboVazio() {
        payload = """
            {
                "combos": [],
                "cliente": {
                    "cpf": "98765432108",
                    "nome": "Fernando",
                    "email": "fernando@email.com"
                }
            }
        """;
    }

    @Dado("o payload de pedido com campos obrigatórios ausentes")
    public void oPayloadDePedidoComCamposObrigatoriosAusentes() {
        payload = """
            {
                "combos": [
                    {
                        "produtos": []
                    }
                ]
            }
        """;
    }

    @Quando("o pedido for criado")
    public void oPedidoForCriado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_PEDIDOS);
    }

    @Então("a resposta deve ter o status {int} OK")
    public void aRespostaDeveTerOStatusOk() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Então("a resposta deve ter o status {int} Bad Request")
    public void aRespostaDeveTerOStatusBadRequest() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Então("a resposta deve ter o status {int} Not Found")
    public void aRespostaDeveTerOStatusNotFound() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
