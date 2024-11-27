package br.com.hexburger.pedido.dominio.entidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.hexburger.pedido.util.PedidoTestUtils.criarCombos;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoTest {

    String cpf = "66885599403";

    @Test
    void deveCriarPedido() {

        LocalDateTime antesDeCriarPedido = LocalDateTime.now();

        Pedido pedido = new Pedido(criarCombos(), cpf);

        LocalDateTime depoisDeCriarPedido = LocalDateTime.now();

        pedido.validaPedido();

        assertThat(pedido, is(notNullValue()));
        assertThat(pedido.getId(), is(notNullValue()));
        assertThat(pedido.getCodigo(), is(nullValue()));
        assertThat(pedido.getCombos(), hasSize(2));
        assertThat(pedido.getValorTotal(), is(BigDecimal.valueOf(84.00)));
        assertThat(pedido.getStatus(), is(equalTo(StatusPedido.RECEBIDO)));
        assertThat(pedido.getCpfCliente(), is(equalTo(cpf)));
        assertThat(pedido.getQrCode(), is(nullValue()));
        assertThat(pedido.getIdExternoPagamento(), is(nullValue()));
        assertThat(!pedido.getDataPedido().isBefore(antesDeCriarPedido), is(true));
        assertThat(!pedido.getDataPedido().isAfter(depoisDeCriarPedido), is(true));

    }

    @Test
    void deveCriarPedidoParaPagamento() {

        LocalDateTime antesDeCriarPedido = LocalDateTime.now();

        Pedido pedido = new Pedido(UUID.randomUUID().toString(), 1, criarCombos(), BigDecimal.valueOf(84.00),
                cpf, StatusPedido.EM_PREPARACAO, LocalDateTime.now(), "qrCode",
                "idExternoPagamento");

        LocalDateTime depoisDeCriarPedido = LocalDateTime.now();

        pedido.validaPedido();

        assertThat(pedido, is(notNullValue()));
        assertThat(pedido.getId(), is(notNullValue()));
        assertThat(pedido.getCodigo(), is(1));
        assertThat(pedido.getCombos(), hasSize(2));
        assertThat(pedido.getValorTotal(), is(BigDecimal.valueOf(84.00)));
        assertThat(pedido.getStatus(), is(equalTo(StatusPedido.EM_PREPARACAO)));
        assertThat(pedido.getCpfCliente(), is(equalTo(cpf)));
        assertThat(!pedido.getDataPedido().isBefore(antesDeCriarPedido), is(true));
        assertThat(!pedido.getDataPedido().isAfter(depoisDeCriarPedido), is(true));
        assertThat(pedido.getIdExternoPagamento(), is(equalTo("idExternoPagamento")));
        assertThat(pedido.getQrCode(), is(equalTo("qrCode")));

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoCombosVazio() {

        Pedido pedido = new Pedido(List.of(), cpf);

        assertThrows(IllegalArgumentException.class, pedido::validaPedido);

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoCombosNulo() {

        Pedido pedido = new Pedido(null, cpf);

        assertThat(pedido.getCombos(), is(notNullValue()));
        assertThrows(IllegalArgumentException.class, pedido::validaPedido);

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoParaPagamentoComCombosNulo() {

        Pedido pedido = new Pedido(UUID.randomUUID().toString(), 1, null, BigDecimal.valueOf(84.00),
                cpf, StatusPedido.EM_PREPARACAO, LocalDateTime.now(), "qrCode",
                "idExternoPagamento");

        assertThrows(IllegalArgumentException.class, pedido::validaPedido);

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoComValorNulo() {

        List<ProdutoPedido> produtosPedido = List.of(new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer",
                "Hamburguer de carne", null, Categoria.LANCHE));
        List<Combo> combos = List.of(new Combo(UUID.randomUUID().toString(), produtosPedido, null));

        Pedido pedido = new Pedido(combos, cpf);

        assertThat(pedido.getValorTotal(), is(equalTo(BigDecimal.ZERO)));
        assertThrows(IllegalArgumentException.class, pedido::validaPedido);

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoParaPagamentoComValorNulo() {

        Pedido pedido = new Pedido(UUID.randomUUID().toString(), 1, criarCombos(), null,
                cpf, StatusPedido.EM_PREPARACAO, LocalDateTime.now(), "qrCode",
                "idExternoPagamento");

        assertThrows(IllegalArgumentException.class, pedido::validaPedido);

    }

    @Test
    void deveLancarExcecaoAoCriarPedidoComValorZero() {

        List<ProdutoPedido> produtosPedido = List.of(new ProdutoPedido(UUID.randomUUID().toString(), "Hamburguer",
                "Hamburguer de carne", BigDecimal.ZERO, Categoria.LANCHE));
        List<Combo> combos = List.of(new Combo(produtosPedido));
        Pedido pedido = new Pedido(combos, cpf);

        assertThrows(IllegalArgumentException.class, pedido::validaPedido);

    }

}
