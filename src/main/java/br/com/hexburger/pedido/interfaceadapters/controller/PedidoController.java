package br.com.hexburger.pedido.interfaceadapters.controller;

import br.com.hexburger.pedido.application.usecase.pedido.BuscarPedidosUseCase;
import br.com.hexburger.pedido.application.usecase.pedido.CriarPedidoUseCase;
import br.com.hexburger.pedido.dominio.entidade.Combo;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;
import br.com.hexburger.pedido.interfaceadapters.dto.PedidoDTO;
import br.com.hexburger.pedido.interfaceadapters.gateway.PedidoGatewayJPA;
import br.com.hexburger.pedido.interfaceadapters.gateway.ProdutoGatewayJPA;
import br.com.hexburger.pedido.interfaceadapters.presenter.PedidoPresenter;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.PedidoRepositorioAdaptador;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.ProdutoRepositorioAdaptador;

import java.util.List;

public class PedidoController {

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO, PedidoRepositorioAdaptador pedidoRepositorio, ProdutoRepositorioAdaptador produtoRepositorio) {
        CriarPedidoUseCase useCase = new CriarPedidoUseCase(new PedidoGatewayJPA(pedidoRepositorio), new ProdutoGatewayJPA(produtoRepositorio));
        return PedidoPresenter.toDTO(useCase.criarPedido(dtoToDomain(pedidoDTO)));
    }

    public List<PedidoDTO> buscarPedidos(PedidoRepositorioAdaptador repositorio) {
        BuscarPedidosUseCase useCase = new BuscarPedidosUseCase(new PedidoGatewayJPA(repositorio));
        return useCase.buscarPedidos().stream().map(PedidoPresenter::toDTO).toList();
    }

    private Pedido dtoToDomain(PedidoDTO pedidoDTO) {
        List<Combo> combos = pedidoDTO.getCombos().stream().map(combo -> new Combo(combo.getProdutos().stream().map(p -> new ProdutoPedido(p.getId())).toList())).toList();
        return new Pedido(combos, pedidoDTO.getCpfCliente());
    }

}
