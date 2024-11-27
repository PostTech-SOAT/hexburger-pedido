package br.com.hexburger.pedido.dominio.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.com.hexburger.pedido.dominio.entidade.StatusPedido.RECEBIDO;
import static java.time.LocalDateTime.now;

public class Pedido {

    private final String id;

    private Integer codigo;

    private List<Combo> combos;

    private BigDecimal valorTotal;

    private final String cpfCliente;

    private final StatusPedido status;

    private final LocalDateTime dataPedido;

    private String qrCode;

    private String idExternoPagamento;

    public Pedido(List<Combo> combos, String cpfCliente) {
        this.id = UUID.randomUUID().toString();
        combos = combos != null ? combos : Collections.emptyList();
        this.combos = combos;
        this.valorTotal = combos.stream().map(Combo::getValorTotal).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.cpfCliente = cpfCliente;
        this.status = RECEBIDO;
        this.dataPedido = now();
    }

    public Pedido(String id, Integer codigo, List<Combo> combos, BigDecimal valorTotal, String cpfCliente, StatusPedido status,
                  LocalDateTime dataPedido, String qrCode, String idExternoPagamento) {
        this.id = id;
        this.codigo = codigo;
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

    public Integer getCodigo() {
        return codigo;
    }

    public List<Combo> getCombos() {
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

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
        this.valorTotal = combos.stream().map(Combo::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void validaPedido() {
        if (!validaCombos() || !validaValorTotal()) {
            throw new IllegalArgumentException("Pedido inválido");
        }
        combos.forEach(Combo::validaCombo);
    }

    private boolean validaCombos() {
        return combos != null && !combos.isEmpty();
    }

    private boolean validaValorTotal() {
        return valorTotal != null && valorTotal.compareTo(BigDecimal.ZERO) > 0;
    }

}
