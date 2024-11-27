package br.com.hexburger.pedido.interfaceadapters.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class ProdutoDTOTest {

    @Test
    void deveCriarProduto() {

        ProdutoDTO produto = new ProdutoDTO(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        assertThat(produto, is(notNullValue()));
        assertThat(produto.getNome(), is(equalTo("Hex Burger")));
        assertThat(produto.getDescricao(), is(equalTo("Pão e Hambuguer no formato hexagonal")));
        assertThat(produto.getValor(), is(equalTo(BigDecimal.valueOf(15.00))));
        assertThat(produto.getCategoria(), is(equalTo(LANCHE)));

    }

}
