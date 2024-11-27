package br.com.hexburger.pedido.interfaceadapters.presenter;

import br.com.hexburger.pedido.dominio.entidade.Pedido;
import br.com.hexburger.pedido.interfaceadapters.dto.ComboDTO;
import br.com.hexburger.pedido.interfaceadapters.dto.PedidoDTO;
import br.com.hexburger.pedido.interfaceadapters.dto.ProdutoPedidoDTO;

import java.util.List;

public class PedidoPresenter {

    public static PedidoDTO toDTO(Pedido pedido) {
        return new PedidoDTO(pedido.getId(), pedido.getCodigo(), getCombosDTO(pedido), pedido.getValorTotal(), pedido.getCpfCliente(),
                pedido.getStatus(), pedido.getDataPedido(), pedido.getQrCode(), pedido.getIdExternoPagamento());
    }

    private static List<ComboDTO> getCombosDTO(Pedido pedido) {
        return pedido.getCombos().stream().map(combo -> new ComboDTO(combo.getId(), combo.getProdutos().stream().map(p ->
                new ProdutoPedidoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getValor(), p.getCategoria())).toList(),
                combo.getValorTotal())).toList();
    }

}
