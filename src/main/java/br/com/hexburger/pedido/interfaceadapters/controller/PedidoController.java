package br.com.hexburger.pedido.interfaceadapters.controller;

import br.com.hexburger.pedido.application.interfaceevent.PedidoSender;
import br.com.hexburger.pedido.application.usecase.pedido.AtualizarStatusPedidoUseCase;
import br.com.hexburger.pedido.application.usecase.pedido.BuscarPedidosUseCase;
import br.com.hexburger.pedido.application.usecase.pedido.CriarPedidoUseCase;
import br.com.hexburger.pedido.dominio.entidade.Combo;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;
import br.com.hexburger.pedido.interfaceadapters.dto.PedidoDTO;
import br.com.hexburger.pedido.interfaceadapters.events.PagamentoConcluidoEvent;
import br.com.hexburger.pedido.interfaceadapters.gateway.PedidoGatewayJPA;
import br.com.hexburger.pedido.interfaceadapters.gateway.ProdutoGatewayJPA;
import br.com.hexburger.pedido.interfaceadapters.presenter.PedidoPresenter;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.PedidoRepositorioAdaptador;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.ProdutoRepositorioAdaptador;

import java.util.List;

import static br.com.hexburger.pedido.dominio.entidade.StatusPedido.CANCELADO;
import static br.com.hexburger.pedido.dominio.entidade.StatusPedido.EM_PREPARACAO;

public class PedidoController {

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO, PedidoSender pedidoSender, PedidoRepositorioAdaptador pedidoRepositorio, ProdutoRepositorioAdaptador produtoRepositorio) {
        CriarPedidoUseCase useCase = new CriarPedidoUseCase(pedidoSender, new PedidoGatewayJPA(pedidoRepositorio), new ProdutoGatewayJPA(produtoRepositorio));
        return PedidoPresenter.toDTO(useCase.criarPedido(dtoToDomain(pedidoDTO)));
    }

    public List<PedidoDTO> buscarPedidos(PedidoRepositorioAdaptador repositorio) {
        BuscarPedidosUseCase useCase = new BuscarPedidosUseCase(new PedidoGatewayJPA(repositorio));
        return useCase.buscarPedidos().stream().map(PedidoPresenter::toDTO).toList();
    }

    public void atualizarStatusPedido(PagamentoConcluidoEvent event, PedidoRepositorioAdaptador repositorio) {
        AtualizarStatusPedidoUseCase useCase = new AtualizarStatusPedidoUseCase(new PedidoGatewayJPA(repositorio));
        useCase.atualizarStatusPedido(event.getIdPedido(), event.isAprovado() ? EM_PREPARACAO : CANCELADO);
    }

    private Pedido dtoToDomain(PedidoDTO pedidoDTO) {
        List<Combo> combos = pedidoDTO.getCombos().stream().map(combo -> new Combo(combo.getProdutos().stream().map(p -> new ProdutoPedido(p.getId())).toList())).toList();
        return new Pedido(combos, pedidoDTO.getCpfCliente());
    }

}
