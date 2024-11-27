package br.com.hexburger.pedido.interfaceadapters.repositorioadaptador;

import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EPedidoInterface;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.PedidoRepositoryDTO;

import java.util.List;

public interface PedidoRepositorioAdaptador {

    EPedidoInterface criarPedido(PedidoRepositoryDTO pedidoRepositoryDTO);

    List<? extends EPedidoInterface> buscarPedidos();

    void atualizarStatusPedido(String idPedido, String statusPedido);

}
