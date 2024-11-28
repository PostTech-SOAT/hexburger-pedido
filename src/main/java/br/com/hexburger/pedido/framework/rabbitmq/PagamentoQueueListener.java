package br.com.hexburger.pedido.framework.rabbitmq;

import br.com.hexburger.pedido.framework.repository.PedidoRepositorioImpl;
import br.com.hexburger.pedido.interfaceadapters.controller.PedidoController;
import br.com.hexburger.pedido.interfaceadapters.events.PagamentoConcluidoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoQueueListener {

    private final PedidoRepositorioImpl repositorio;

    @RabbitListener(queues = "pagamento-queue")
    public void receiveMessage(PagamentoConcluidoEvent pagamentoConcluidoEvent) {

        PedidoController controller = new PedidoController();
        controller.atualizarStatusPedido(pagamentoConcluidoEvent, repositorio);

    }

}