package br.com.hexburger.pedido.interfaceadapters.gateway;

import br.com.hexburger.pedido.application.interfacegateway.ProdutoGateway;
import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.Produto;
import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EProdutoInterface;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.ProdutoRepositorioAdaptador;

import java.util.List;
import java.util.Optional;

public class ProdutoGatewayJPA implements ProdutoGateway {

    private final ProdutoRepositorioAdaptador repository;

    public ProdutoGatewayJPA(ProdutoRepositorioAdaptador repository) {
        this.repository = repository;
    }

    @Override
    public Produto criarProduto(Produto produto) {
        EProdutoInterface eProduto = repository.criarProduto(produto.getId(), produto.getNome(),
                produto.getDescricao(), produto.getValor(), produto.getCategoria().name());
        return new Produto(eProduto.getId(), eProduto.getNome(), eProduto.getDescricao(), eProduto.getValor(),
                Categoria.valueOf(eProduto.getCategoria()));
    }

    @Override
    public Optional<Produto> buscarProdutoPorNome(String nome) {
        return repository.buscarProdutoPorNome(nome).map(eProduto -> new Produto(eProduto.getId(), eProduto.getNome(),
                eProduto.getDescricao(), eProduto.getValor(), Categoria.valueOf(eProduto.getCategoria())));
    }

    @Override
    public Optional<Produto> buscarProdutoPorId(String id) {
        return repository.buscarProdutoPorId(id).map(eProduto -> new Produto(eProduto.getId(), eProduto.getNome(),
                eProduto.getDescricao(), eProduto.getValor(), Categoria.valueOf(eProduto.getCategoria())));
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Categoria categoria) {
        return repository.buscarProdutosPorCategoria(categoria.name()).stream().map(eProduto ->
                new Produto(eProduto.getId(), eProduto.getNome(), eProduto.getDescricao(), eProduto.getValor(),
                        Categoria.valueOf(eProduto.getCategoria()))).toList();
    }

    @Override
    public Produto editarProduto(Produto produto) {
        EProdutoInterface eProduto = repository.editarProduto(produto.getId(), produto.getNome(),
                produto.getDescricao(), produto.getValor(), produto.getCategoria().name());
        return new Produto(eProduto.getId(), eProduto.getNome(), eProduto.getDescricao(), eProduto.getValor(),
                Categoria.valueOf(eProduto.getCategoria()));
    }

    @Override
    public void removerProduto(String id) {
        repository.removerProduto(id);
    }
}
