package br.com.hexburger.pedido.interfaceadapters.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PagamentoConcluidoEvent implements Serializable {

    private String idPedido;

    private String status;

    public boolean isAprovado() {
        return status.equals("APROVADO");
    }

}
