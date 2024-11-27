package br.com.hexburger.pedido.dominio.entidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ProdutoPedidoTest {

    @Test
    void deveCriarProdutoPedido() {

        ProdutoPedido produtoPedido = new ProdutoPedido("5c57127a-a80b-4b58-a2ce-b1ee12158766", "Hamburguer", "Hamburguer de carne",
                BigDecimal.valueOf(15.00), Categoria.LANCHE);

        assertThat(produtoPedido, is(notNullValue()));
        assertThat(produtoPedido.getNome(), is(equalTo("Hamburguer")));
        assertThat(produtoPedido.getDescricao(), is(equalTo("Hamburguer de carne")));
        assertThat(produtoPedido.getValor(), is(equalTo(BigDecimal.valueOf(15.00))));
        assertThat(produtoPedido.getCategoria(), is(equalTo(Categoria.LANCHE)));

    }

    @Test
    void deveCriarProdutoPedidoPorId() {

        ProdutoPedido produtoPedido = new ProdutoPedido("5c57127a-a80b-4b58-a2ce-b1ee12158766");

        assertThat(produtoPedido, is(notNullValue()));
        assertThat(produtoPedido.getId(), is(equalTo("5c57127a-a80b-4b58-a2ce-b1ee12158766")));
        assertThat(produtoPedido.getValor(), is(equalTo(BigDecimal.ZERO)));

    }

}
