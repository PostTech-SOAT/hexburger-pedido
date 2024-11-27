package br.com.hexburger.pedido.interfaceadapters.repositorioadaptador;

import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EProdutoInterface;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorioAdaptador {

    EProdutoInterface criarProduto(String id, String nome, String descricao, BigDecimal valor, String categoria);
    
    Optional<? extends EProdutoInterface> buscarProdutoPorNome(String nome);
    
    Optional<? extends EProdutoInterface> buscarProdutoPorId(String id);
    
    List<? extends EProdutoInterface> buscarProdutosPorCategoria(String categoria);

    EProdutoInterface editarProduto(String id, String nome, String descricao, BigDecimal valor, String categoria);
    
    void removerProduto(String id);
    
}
