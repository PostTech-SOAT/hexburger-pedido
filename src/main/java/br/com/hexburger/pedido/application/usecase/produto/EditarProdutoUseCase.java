package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ResourceNotFoundException;
import br.com.hexburger.pedido.dominio.entidade.Produto;

public class EditarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public EditarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto editarProduto(Produto produto) {
        produtoGateway.buscarProdutoPorId(produto.getId()).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return produtoGateway.editarProduto(produto);
    }

}
