package br.com.hexburger.pedido.application.interfacegateway;

import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Produto criarProduto(Produto produto);
    Optional<Produto> buscarProdutoPorNome(String nome);
    Optional<Produto> buscarProdutoPorId(String id);
    List<Produto> buscarProdutosPorCategoria(Categoria categoria);
    Produto editarProduto(Produto produto);
    void removerProduto(String id);

}
