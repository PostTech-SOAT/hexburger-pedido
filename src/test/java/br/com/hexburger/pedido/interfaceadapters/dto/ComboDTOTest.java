package br.com.hexburger.pedido.interfaceadapters.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.Categoria.ACOMPANHAMENTO;
import static br.com.hexburger.pedido.dominio.entidade.Categoria.LANCHE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ComboDTOTest {

    @Test
    void deveCriarCombo() {

        ProdutoPedidoDTO lanche = new ProdutoPedidoDTO(UUID.randomUUID().toString(), "Hex Burger",
                "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), LANCHE);
        ProdutoPedidoDTO batataFrita = new ProdutoPedidoDTO(UUID.randomUUID().toString(), "Hex Fries",
                "Batata frita no formato hexagonal", BigDecimal.valueOf(6.00), ACOMPANHAMENTO);

        ComboDTO combo = new ComboDTO(UUID.randomUUID().toString(), List.of(lanche, batataFrita), BigDecimal.valueOf(21.00));

        assertThat(combo, is(notNullValue()));
        assertThat(combo.getId(), is(notNullValue()));
        assertThat(combo.getProdutos(), is(notNullValue()));
        assertThat(combo.getProdutos().size(), is(equalTo(2)));
        assertThat(combo.getValorTotal(), is(equalTo(BigDecimal.valueOf(21.00))));

    }

}
