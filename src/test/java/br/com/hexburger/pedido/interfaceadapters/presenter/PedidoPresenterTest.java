package br.com.hexburger.pedido.interfaceadapters.presenter;

import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.interfaceadapters.dto.PedidoDTO;
import org.junit.jupiter.api.Test;

import static br.com.hexburger.pedido.util.PedidoTestUtils.criarCombos;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class PedidoPresenterTest {

    @Test
    void deveConverterDeEntidadeParaDTO() {


        Pedido pedido = new Pedido(criarCombos(), "12345678988");

        PedidoDTO pedidoDTO = PedidoPresenter.toDTO(pedido);

        assertThat(pedidoDTO.getId(), is(equalTo(pedido.getId())));
        assertThat(pedidoDTO.getCodigo(), is(equalTo(pedido.getCodigo())));
        assertThat(pedidoDTO.getCombos().size(), is(equalTo(pedido.getCombos().size())));
        assertThat(pedidoDTO.getValorTotal(), is(equalTo(pedido.getValorTotal())));
        assertThat(pedidoDTO.getCpfCliente(), is(equalTo(pedido.getCpfCliente())));
        assertThat(pedidoDTO.getStatus(), is(equalTo(pedido.getStatus())));
        assertThat(pedidoDTO.getDataPedido(), is(equalTo(pedido.getDataPedido())));
        assertThat(pedidoDTO.getQrCode(), is(equalTo(pedido.getQrCode())));
        assertThat(pedidoDTO.getIdExternoPagamento(), is(equalTo(pedido.getIdExternoPagamento())));

    }

}
