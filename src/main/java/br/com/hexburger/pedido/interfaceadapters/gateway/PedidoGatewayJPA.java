package br.com.hexburger.pedido.interfaceadapters.gateway;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.dominio.entidade.*;
import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EPedidoInterface;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.ComboRepositoryDTO;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.PedidoRepositoryDTO;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.ProdutoPedidoRepositoryDTO;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.PedidoRepositorioAdaptador;

import java.util.List;

public class PedidoGatewayJPA implements PedidoGateway {

    private final PedidoRepositorioAdaptador repository;

    public PedidoGatewayJPA(PedidoRepositorioAdaptador repository) {
        this.repository = repository;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        return entityToDomain(repository.criarPedido(domainToRepositoryDto(pedido)));
    }

    @Override
    public List<Pedido> buscarPedidos() {
        return repository.buscarPedidos().stream().map(this::entityToDomain).toList();
    }

    @Override
    public void atualizarStatusPedido(String idPedido, StatusPedido statusPedido) {
        repository.atualizarStatusPedido(idPedido, statusPedido.name());
    }

    private PedidoRepositoryDTO domainToRepositoryDto(Pedido pedido) {
        List<ComboRepositoryDTO> combosDTO = pedido.getCombos().stream().map(combo -> new ComboRepositoryDTO(combo.getId(), combo.getProdutos().stream().map(p ->
                new ProdutoPedidoRepositoryDTO(p.getId(), p.getNome(), p.getDescricao(), p.getValor(), p.getCategoria())).toList(),
                combo.getValorTotal())).toList();
        return new PedidoRepositoryDTO(pedido.getId(), combosDTO, pedido.getValorTotal(), pedido.getCpfCliente(), pedido.getStatus(), pedido.getDataPedido(),
                pedido.getQrCode(), pedido.getIdExternoPagamento());
    }

    private Pedido entityToDomain(EPedidoInterface ePedidoInterface) {
        List<Combo> combos = ePedidoInterface.getCombos().stream().map(combo -> new Combo(combo.getId(), combo.getProdutosPedido().stream().map(p ->
                new ProdutoPedido(p.getId(), p.getNome(), p.getDescricao(), p.getValor(), Categoria.valueOf(p.getCategoria()))).toList(),
                combo.getValorTotal())).toList();
        return new Pedido(ePedidoInterface.getId(), ePedidoInterface.getCodigo(), combos,
                ePedidoInterface.getValorTotal(), ePedidoInterface.getCpfCliente(), StatusPedido.valueOf(ePedidoInterface.getStatus()),
                ePedidoInterface.getDataPedido(), ePedidoInterface.getQrCode(), ePedidoInterface.getIdExternoPagamento());
    }

}
