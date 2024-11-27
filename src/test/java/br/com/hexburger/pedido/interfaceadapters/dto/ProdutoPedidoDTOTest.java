package br.com.hexburger.pedido.interfaceadapters.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ProdutoPedidoDTOTest {

    @Test
    void deveCriarProdutoPedido() {

        ProdutoPedidoDTO lanche = new ProdutoPedidoDTO(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);

        assertThat(lanche, is(notNullValue()));
        assertThat(lanche.getId(), is(notNullValue()));
        assertThat(lanche.getNome(), is("Hex Burger"));
        assertThat(lanche.getDescricao(), is("Pão e Hambuguer no formato hexagonal"));
        assertThat(lanche.getValor(), is(BigDecimal.valueOf(15.00)));
        assertThat(lanche.getCategoria(), is(LANCHE));

    }
}
