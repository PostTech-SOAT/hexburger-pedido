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

        // todo adicionar os inserts de um pedido para cada status de pedido e comparar a igualdade nesse teste


    }

}
