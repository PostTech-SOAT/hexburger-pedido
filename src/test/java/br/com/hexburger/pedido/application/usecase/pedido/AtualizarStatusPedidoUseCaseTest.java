package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.dominio.entidade.StatusPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.hexburger.pedido.util.PedidoTestUtils.criarCombos;
import static org.mockito.Mockito.verify;

class AtualizarStatusPedidoUseCaseTest {

    private AtualizarStatusPedidoUseCase useCase;

    @Mock
    private PedidoGateway pedidoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {

        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new AtualizarStatusPedidoUseCase(pedidoGateway);

    }

    @Test
    void deveAtualizarStatusPedido() {

        Pedido pedido = new Pedido(criarCombos(), "12345678900");

        useCase.atualizarStatusPedido(pedido.getId(), StatusPedido.RECEBIDO);

        verify(pedidoGateway).atualizarStatusPedido(pedido.getId(), StatusPedido.RECEBIDO);

    }

}
