package br.com.hexburger.pedido.dominio.entidade;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Collections.frequency;

public class Combo {

    private final String id;

    private final List<ProdutoPedido> produtos;

    private final BigDecimal valorTotal;

    public Combo(String id, List<ProdutoPedido> produtos, BigDecimal valorTotal) {
        this.id = id;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public Combo(List<ProdutoPedido> produtos) {
        this.id = UUID.randomUUID().toString();
        produtos = produtos != null ? produtos : Collections.emptyList();
        this.produtos = produtos;
        this.valorTotal = produtos.stream().map(ProdutoPedido::getValor).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getId() {
        return id;
    }

    public List<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void validaCombo() {
        if (!validaProdutos() || !validaValorTotal()) {
            throw new IllegalArgumentException("Combo inválido");
        }
    }

    private boolean validaProdutos() {
        List<Categoria> categorias = produtos.stream().map(ProdutoPedido::getCategoria).toList();
        return !produtos.isEmpty() && categorias.stream().noneMatch(categoria -> frequency(categorias, categoria) > 1);
    }

    private boolean validaValorTotal() {
        return valorTotal != null && valorTotal.compareTo(BigDecimal.ZERO) > 0;
    }
}
