package br.com.hexburger.pedido.interfaceadapters.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PedidoCriadoEvent implements Serializable {

    private String idPedido;

    private BigDecimal valorTotal;

}