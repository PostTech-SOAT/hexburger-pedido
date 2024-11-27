package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.Combo;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;
import br.com.hexburger.pedido.framework.repository.PedidoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.PedidoRepository;
import br.com.hexburger.pedido.framework.repository.ProdutoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.ProdutoRepository;
import br.com.hexburger.pedido.interfaceadapters.gateway.PedidoGatewayJPA;
import br.com.hexburger.pedido.interfaceadapters.gateway.ProdutoGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class CriarPedidoUseCaseIT {

    private CriarPedidoUseCase useCase;

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {

        openMocks = MockitoAnnotations.openMocks(this);

        PedidoGateway pedidoGateway = new PedidoGatewayJPA(new PedidoRepositorioImpl(repository));
        ProdutoGateway produtoGateway = new ProdutoGatewayJPA(new ProdutoRepositorioImpl(produtoRepository));

        useCase = new CriarPedidoUseCase(pedidoGateway, produtoGateway);

    }

    @Test
    void deveCriarPedido() {

        Pedido pedido = new Pedido(criarCombos(), "12345678900");

        Pedido pedidoCriado = useCase.criarPedido(pedido);

        assertThat(pedidoCriado, is(notNullValue()));
        assertThat(pedidoCriado.getId(), is(equalTo(pedido.getId())));
        assertThat(pedidoCriado.getCpfCliente(), is(equalTo("12345678900")));
        assertThat(pedidoCriado.getCombos().size(), is(equalTo(pedido.getCombos().size())));
        assertThat(pedidoCriado.getStatus(), is(equalTo(pedido.getStatus())));
        assertThat(pedidoCriado.getValorTotal(), is(equalTo(pedido.getValorTotal())));
        assertThat(pedidoCriado.getDataPedido(), is(equalTo(pedido.getDataPedido())));
        assertThat(pedidoCriado.getQrCode(), is(equalTo(pedido.getQrCode())));

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoInvalido() {

        Pedido pedidoSemCombos = new Pedido(List.of(), "12345678900");

        assertThrows(IllegalArgumentException.class, () -> useCase.criarPedido(pedidoSemCombos));

    }

    private static List<Combo> criarCombos() {

        return List.of(new Combo(criarProdutosPedido()), new Combo(criarProdutosPedido()));

    }

    private static List<ProdutoPedido> criarProdutosPedido() {

        return List.of(
                new ProdutoPedido("5c4c83cb-f1e8-4182-8601-281323f00111", "Hex Burger", "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), Categoria.LANCHE),
                new ProdutoPedido("f55f733e-aeb3-4955-87d7-b521895cae80", "Hex Fries", "Batatas fritas crocantes", BigDecimal.valueOf(8.00), Categoria.ACOMPANHAMENTO),
                new ProdutoPedido("a1fe5443-3bc7-4cb0-8ffe-a1dc0bf8768c", "Água", "Água", BigDecimal.valueOf(4.00), Categoria.BEBIDA),
                new ProdutoPedido("7cbc859a-a702-4f64-9f81-2136c338338c", "Hex Gelatto", "Sorvete de creme 200ml", BigDecimal.valueOf(15.00), Categoria.SOBREMESA)
        );

    }

}
