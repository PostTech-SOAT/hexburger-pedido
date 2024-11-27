package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.dominio.entidade.Pedido;

import java.util.List;

public class BuscarPedidosUseCase {

    private final PedidoGateway pedidoGateway;

    public BuscarPedidosUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public List<Pedido> buscarPedidos() {
        return pedidoGateway.buscarPedidos();
    }
}
