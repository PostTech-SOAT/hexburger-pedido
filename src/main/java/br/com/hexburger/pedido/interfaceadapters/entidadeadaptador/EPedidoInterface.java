package br.com.hexburger.pedido.interfaceadapters.entidadeadaptador;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface EPedidoInterface {

    String getId();

    Integer getCodigo();

    List<? extends EComboInterface> getCombos();

    BigDecimal getValorTotal();

    String getCpfCliente();

    String getStatus();

    LocalDateTime getDataPedido();

    String getQrCode();

    String getIdExternoPagamento();

}
