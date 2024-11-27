package br.com.hexburger.pedido.application.usecase.produto;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.application.util.exception.ConflictException;
import br.com.hexburger.pedido.dominio.entidade.Produto;

public class CriarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public CriarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto criarProduto(Produto produto) {
        produtoGateway.buscarProdutoPorNome(produto.getNome()).ifPresent(p -> {
            throw new ConflictException("Produto já cadastrado");
        });
        return produtoGateway.criarProduto(produto);
    }
}
