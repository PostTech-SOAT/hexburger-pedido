package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;

import java.util.UUID;

public class BuscarProdutoPorIdUseCase {

    private final ProdutoGateway produtoGateway;

    public BuscarProdutoPorIdUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ProdutoPedido buscaProduto(ProdutoPedido produtoPedido) {
        Produto produto = produtoGateway.buscarProdutoPorId(produtoPedido.getId()).orElseThrow(() -> new ResourceNotFoundException("Produto não existente"));
        return new ProdutoPedido(UUID.randomUUID().toString(), produto.getNome(), produto.getDescricao(), produto.getValor(), produto.getCategoria());
    }

}
