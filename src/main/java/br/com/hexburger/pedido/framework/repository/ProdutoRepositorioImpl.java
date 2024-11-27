package br.com.hexburger.pedido.framework.repository;

import br.com.hexburger.pedido.framework.entidade.EProduto;
import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EProdutoInterface;
import br.com.hexburger.pedido.interfaceadapters.repositorioadaptador.ProdutoRepositorioAdaptador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProdutoRepositorioImpl implements ProdutoRepositorioAdaptador {

    private final ProdutoRepository repository;

    @Override
    public EProdutoInterface criarProduto(String id, String nome, String descricao, BigDecimal valor, String categoria) {
        return repository.save(new EProduto(id, nome, descricao, valor, categoria));
    }

    @Override
    public Optional<? extends EProdutoInterface> buscarProdutoPorNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public Optional<? extends EProdutoInterface> buscarProdutoPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<? extends EProdutoInterface> buscarProdutosPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    public EProdutoInterface editarProduto(String id, String nome, String descricao, BigDecimal valor, String categoria) {
        return repository.save(new EProduto(id, nome, descricao, valor, categoria));
    }

    @Override
    public void removerProduto(String id) {
        repository.deleteById(id);
    }

}
