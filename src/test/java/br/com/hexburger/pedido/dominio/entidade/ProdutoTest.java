package br.com.hexburger.pedido.dominio.entidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProdutoTest {

    @Test
    void deveCriarProduto() {

        Produto produto = new Produto(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        assertThat(produto, is(notNullValue()));
        assertThat(produto.getId(), is(notNullValue()));
        assertThat(produto.getNome(), is(equalTo("Hex Burger")));
        assertThat(produto.getDescricao(), is(equalTo("Pão e Hambuguer no formato hexagonal")));
        assertThat(produto.getValor(), is(equalTo(BigDecimal.valueOf(15.00))));
        assertThat(produto.getCategoria(), is(equalTo(LANCHE)));

    }

    @Test
    void deveCriarProdutoComIdAleatorio() {

        Produto produto = new Produto("Hex Burger", "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        assertThat(produto, is(notNullValue()));
        assertThat(UUID.fromString(produto.getId()), is(notNullValue()));
        assertThat(produto.getNome(), is(equalTo("Hex Burger")));
        assertThat(produto.getDescricao(), is(equalTo("Pão e Hambuguer no formato hexagonal")));
        assertThat(produto.getValor(), is(equalTo(BigDecimal.valueOf(15.00))));
        assertThat(produto.getCategoria(), is(equalTo(LANCHE)));

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoComNomeInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), "",
                "", BigDecimal.valueOf(15.00), LANCHE));
        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), null,
                null, BigDecimal.valueOf(15.00), LANCHE));

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoComDescricaoInvalida() {

        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), "Hex Burger",
                "", BigDecimal.valueOf(15.00), LANCHE));
        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), "Hex Burger",
                null, BigDecimal.valueOf(15.00), LANCHE));

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoComValorInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.ZERO, LANCHE));
        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", null, LANCHE));

    }

    @Test
    void deveLancarExcecaoAoCriarProdutoComCategoriaInvalida() {

        assertThrows(IllegalArgumentException.class, () -> new Produto(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), null));

    }

}
