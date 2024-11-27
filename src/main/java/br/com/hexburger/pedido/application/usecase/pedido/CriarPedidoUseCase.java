package br.com.hexburger.pedido.application.usecase.pedido;

import br.com.hexburger.pedido.application.interfacegateway.PedidoGateway;
import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.usecase.produto.BuscarProdutoPorIdUseCase;
import br.com.hexburger.pedido.dominio.entidade.Combo;
import br.com.hexburger.pedido.dominio.entidade.Pedido;

public class CriarPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;

    public CriarPedidoUseCase(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setCombos(pedido.getCombos().stream().map(combo -> new Combo(combo.getProdutos().stream()
                .map(produto -> new BuscarProdutoPorIdUseCase(produtoGateway).buscaProduto(produto)).toList()))
                .toList());
        pedido.validaPedido();
        return pedidoGateway.criarPedido(pedido);
    }

}
