package br.com.hexburger.pedido.interfaceadapters.gateway.repositorydto;

import br.com.hexburger.pedido.dominio.entidade.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoRepositoryDTO {

    private final String id;

    private final List<ComboRepositoryDTO> combos;

    private final BigDecimal valorTotal;

    private final String cpfCliente;

    private final StatusPedido status;

    private final LocalDateTime dataPedido;

    private final String qrCode;

    private final String idExternoPagamento;

    public PedidoRepositoryDTO(String id, List<ComboRepositoryDTO> combos, BigDecimal valorTotal, String cpfCliente, StatusPedido status, LocalDateTime dataPedido, String qrCode, String idExternoPagamento) {
        this.id = id;
        this.combos = combos;
        this.valorTotal = valorTotal;
        this.cpfCliente = cpfCliente;
        this.status = status;
        this.dataPedido = dataPedido;
        this.qrCode = qrCode;
        this.idExternoPagamento = idExternoPagamento;
    }

    public String getId() {
        return id;
    }

    public List<ComboRepositoryDTO> getCombos() {
        return combos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getIdExternoPagamento() {
        return idExternoPagamento;
    }

}
