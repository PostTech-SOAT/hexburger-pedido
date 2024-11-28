package br.com.hexburger.pedido.application.interfaceevent;

import java.math.BigDecimal;

public interface PedidoSender {

    void send(String id, BigDecimal valorTotal);

}
