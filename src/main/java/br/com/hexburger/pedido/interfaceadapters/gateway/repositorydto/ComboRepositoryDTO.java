package br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto;

import java.math.BigDecimal;
import java.util.List;

public class ComboRepositoryDTO {

    private final String id;

    private final List<ProdutoPedidoRepositoryDTO> produtos;

    private final BigDecimal valorTotal;

    public ComboRepositoryDTO(String id, List<ProdutoPedidoRepositoryDTO> produtos, BigDecimal valorTotal) {
        this.id = id;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public List<ProdutoPedidoRepositoryDTO> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

}
