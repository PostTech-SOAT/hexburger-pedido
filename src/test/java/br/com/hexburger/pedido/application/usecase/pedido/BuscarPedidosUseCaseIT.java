package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.framework.repository.PedidoRepositorioImpl;
import br.com.hexburger.pedido.framework.repository.PedidoRepository;
import br.com.hexburger.pedido.interfaceadapters.gateway.PedidoGatewayJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static br.com.hexburger.pedido.dominio.entidade.StatusPedido.CANCELADO;
import static br.com.hexburger.pedido.dominio.entidade.StatusPedido.FINALIZADO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@ActiveProfiles("test")
class BuscarPedidosUseCaseIT {

    private BuscarPedidosUseCase useCase;

    @Autowired
    private PedidoRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {

        openMocks = MockitoAnnotations.openMocks(this);

        PedidoGateway pedidoGateway = new PedidoGatewayJPA(new PedidoRepositorioImpl(repository));

        useCase = new BuscarPedidosUseCase(pedidoGateway);

    }

    @Test
    void deveBuscarPedidos() {

        List<Pedido> pedidos = useCase.buscarPedidos();

        assertThat(pedidos, is(not(empty())));
        pedidos.forEach(pedido -> {
            assertThat(pedido.getStatus().name(), is(not(equalTo(FINALIZADO.name()))));
            assertThat(pedido.getStatus().name(), is(not(equalTo(CANCELADO.name()))));
        });

    }

}
