package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static br.com.hexburger.pedido.util.PedidoTestUtils.criarCombos;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

class BuscarPedidosUseCaseTest {

    private BuscarPedidosUseCase useCase;

    @Mock
    private PedidoGateway pedidoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarPedidosUseCase(pedidoGateway);

    }

    @Test
    void deveBuscarPedidos() {

        when(pedidoGateway.buscarPedidos()).thenReturn(criarPedidos());

        List<Pedido> pedidos = useCase.buscarPedidos();

        assertThat(pedidos, is(notNullValue()));
        assertThat(pedidos, hasSize(2));

    }

    private List<Pedido> criarPedidos() {

        return List.of(new Pedido(criarCombos(), "12345678900"), new Pedido(criarCombos(), "12345678900"));

    }

}
