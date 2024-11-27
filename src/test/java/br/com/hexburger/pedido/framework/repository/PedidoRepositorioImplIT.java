package br.com.hexburger.pedido.framework.repository;

import br.com.hexburger.pedido.framework.entidade.EPedido;
import br.com.hexburger.pedido.interfaceadapters.entidadeadaptador.EPedidoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.StatusPedido.*;
import static br.com.hexburger.pedido.util.PedidoTestUtils.criarECombos;
import static br.com.hexburger.pedido.util.PedidoTestUtils.criarPedidoRepositoryDTO;
import static java.time.LocalDateTime.now;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class PedidoRepositorioImplIT {

    @Autowired
    private PedidoRepository repository;

    private PedidoRepositorioImpl pedidoRepositorio;

    @BeforeEach
    void setup() {

        pedidoRepositorio = new PedidoRepositorioImpl(repository);

    }

    @Test
    void deveCriarPedido() {

        EPedido pedido = new EPedido(UUID.randomUUID().toString(), criarECombos(), BigDecimal.valueOf(84.00),
                "78768950988", RECEBIDO.name(), now(), null, null);


        EPedidoInterface pedidoCriado = pedidoRepositorio.criarPedido(criarPedidoRepositoryDTO());

        assertThat(pedidoCriado, is(notNullValue()));
        assertThat(pedidoCriado.getId(), is(notNullValue()));
        assertThat(pedidoCriado.getCombos().size(), is(equalTo(pedido.getCombos().size())));
        assertThat(pedidoCriado.getValorTotal(), is(equalTo(pedido.getValorTotal())));
        assertThat(pedidoCriado.getCpfCliente(), is(equalTo(pedido.getCpfCliente())));
        assertThat(pedidoCriado.getStatus(), is(equalTo(pedido.getStatus())));
        assertThat(pedidoCriado.getDataPedido(), is(notNullValue()));
        assertThat(pedidoCriado.getQrCode(), is(equalTo(pedido.getQrCode())));
        assertThat(pedidoCriado.getIdExternoPagamento(), is(equalTo(pedido.getIdExternoPagamento())));

    }

    @Test
    void deveBuscarPedidos() {

        assertTrue(pedidoRepositorio.buscarPedidos().stream().map(EPedidoInterface::getStatus)
                .noneMatch(status -> status.equals(FINALIZADO.name()) || status.equals(CANCELADO.name())));

    }

    @Test
    void deveLancarExcecaoAoAtualizarStatusPedidoInexistente() {

        assertThrows(Exception.class, () -> pedidoRepositorio.atualizarStatusPedido("idInexistente", "APROVADO"));

    }

}
