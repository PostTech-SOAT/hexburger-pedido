package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.dominio.entidade.*;
import br.com.hexburger.pedido.framework.rabbitmq.PedidoSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CriarPedidoUseCaseTest {

    private CriarPedidoUseCase useCase;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private PedidoSenderService pedidoSenderService;

    AutoCloseable openMocks;

    private Pedido pedido;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CriarPedidoUseCase(pedidoSenderService, pedidoGateway, produtoGateway);
        pedido = new Pedido(criarCombosPadrao(), "12345678900");

    }

    @Test
    void deveCriarPedido() {

        when(pedidoGateway.criarPedido(any(Pedido.class))).thenReturn(pedido);
        when(produtoGateway.buscarProdutoPorId(any(String.class)))
                .thenAnswer(invocation -> Optional.of(criarProdutoById(invocation.getArgument(0))));

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

    private List<Combo> criarCombosPadrao() {

        return List.of(new Combo(criarProdutosPedido()), new Combo(criarProdutosPedido()));

    }

    private List<ProdutoPedido> criarProdutosPedido() {

        return List.of(
                new ProdutoPedido("d38510b2-53c6-4eba-840e-db44ec8594c0", "Hex Burger",
                        "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), Categoria.LANCHE),
                new ProdutoPedido("7dcb687e-f74f-44c9-84fc-dd336afcb0a7", "Hex Fries",
                        "Batatas fritas crocantes", BigDecimal.valueOf(8.00), Categoria.ACOMPANHAMENTO),
                new ProdutoPedido("2cf80623-8c43-491f-9e1a-db8e08687b12", "Água",
                        "Água", BigDecimal.valueOf(4.00), Categoria.BEBIDA),
                new ProdutoPedido("d5cbe9de-2989-4ca5-9be7-4ca1426ae2b4", "Hex Gelatto",
                        "Sorvete de creme 200ml", BigDecimal.valueOf(15.00), Categoria.SOBREMESA)
        );

    }

    private Produto criarProdutoById(String id) {

        List<ProdutoPedido> produtosPedidos = pedido.getCombos().stream().map(Combo::getProdutos).flatMap(List::stream).toList();
        ProdutoPedido produtoPedidoAlvo = produtosPedidos.stream().filter(produtoPedido -> produtoPedido.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        return new Produto(produtoPedidoAlvo.getId(), produtoPedidoAlvo.getNome(), produtoPedidoAlvo.getDescricao(), produtoPedidoAlvo.getValor(), produtoPedidoAlvo.getCategoria());

    }

}
