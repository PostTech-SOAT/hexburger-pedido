package br.com.hexburger.pedido.application.interfacegateway;

import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.dominio.entidade.StatusPedido;

import java.util.List;

public interface PedidoGateway {

    Pedido criarPedido(Pedido pedido);
    List<Pedido> buscarPedidos();
    void atualizarStatusPedido(String idPedido, StatusPedido statusPedido);

}
