package br.com.hexburger.pedido.interfaceadapters.dto;

import java.math.BigDecimal;
import java.util.List;

public class ComboDTO {

    private final String id;

    private final List<ProdutoPedidoDTO> produtos;

    private final BigDecimal valorTotal;

    public ComboDTO(String id, List<ProdutoPedidoDTO> produtos, BigDecimal valorTotal) {
        this.id = id;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public List<ProdutoPedidoDTO> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

}
