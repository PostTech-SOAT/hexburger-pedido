package br.com.hexburger.pedido.util;

import br.com.hexburger.pedido.dominio.entidade.Categoria;
import br.com.hexburger.pedido.dominio.entidade.Combo;
import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.dominio.entidade.ProdutoPedido;
import br.com.hexburger.pedido.framework.entidade.ECombo;
import br.com.hexburger.pedido.framework.entidade.EProdutoPedido;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.ComboRepositoryDTO;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.PedidoRepositoryDTO;
import br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto.ProdutoPedidoRepositoryDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PedidoTestUtils {

    public static List<Combo> criarCombos() {

        return List.of(new Combo(criarProdutosPedido()), new Combo(criarProdutosPedido()));

    }

    public static List<ProdutoPedido> criarProdutosPedido() {

        return List.of(
                new ProdutoPedido(UUID.randomUUID().toString(), "Hex Burger", "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), Categoria.LANCHE),
                new ProdutoPedido(UUID.randomUUID().toString(), "Hex Fries", "Batatas fritas crocantes", BigDecimal.valueOf(8.00), Categoria.ACOMPANHAMENTO),
                new ProdutoPedido(UUID.randomUUID().toString(), "Água", "Água", BigDecimal.valueOf(4.00), Categoria.BEBIDA),
                new ProdutoPedido(UUID.randomUUID().toString(), "Hex Gelatto", "Sorvete de creme 200ml", BigDecimal.valueOf(15.00), Categoria.SOBREMESA)
        );

    }

    public static List<ECombo> criarECombos() {

        return List.of(new ECombo("70b20b4c-d2da-45f2-9ab3-1c13396071b0", criarEProdutosPedido(), BigDecimal.valueOf(42.00)),
                new ECombo("a1fa3967-0c05-41e1-8d30-2a298121656f", criarEProdutosPedido(), BigDecimal.valueOf(42.00)));

    }

    public static List<EProdutoPedido> criarEProdutosPedido() {

        return List.of(
                new EProdutoPedido(UUID.randomUUID().toString(), "Hex Burger", "Pão e Hambuguer no formato hexagonal", BigDecimal.valueOf(15.00), Categoria.LANCHE.name()),
                new EProdutoPedido(UUID.randomUUID().toString(), "Hex Fries", "Batatas fritas crocantes", BigDecimal.valueOf(8.00), Categoria.ACOMPANHAMENTO.name()),
                new EProdutoPedido(UUID.randomUUID().toString(), "Água", "Água", BigDecimal.valueOf(4.00), Categoria.BEBIDA.name()),
                new EProdutoPedido(UUID.randomUUID().toString(), "Hex Gelatto", "Sorvete de creme 200ml", BigDecimal.valueOf(15.00), Categoria.SOBREMESA.name()));

    }

    public static PedidoRepositoryDTO criarPedidoRepositoryDTO() {
        Pedido pedido = new Pedido(criarCombos(), "78768950988");
        List<ComboRepositoryDTO> combosDTO = pedido.getCombos().stream().map(combo -> new ComboRepositoryDTO(combo.getId(), combo.getProdutos().stream().map(p ->
                new ProdutoPedidoRepositoryDTO(p.getId(), p.getNome(), p.getDescricao(), p.getValor(), p.getCategoria())).toList(),
                combo.getValorTotal())).toList();
        return new PedidoRepositoryDTO(pedido.getId(), combosDTO, pedido.getValorTotal(), pedido.getCpfCliente(), pedido.getStatus(), pedido.getDataPedido(),
                pedido.getQrCode(), pedido.getIdExternoPagamento());
    }

}
