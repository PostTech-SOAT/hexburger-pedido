package br.com.hexburger.pedido.framework.rabbitmq;

import br.com.hexburger.pedido.application.interfaceevent.PedidoSender;
import br.com.hexburger.pedido.interfaceadapters.events.PedidoCriadoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PedidoSenderService implements PedidoSender {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String id, BigDecimal valorTotal) {
        PedidoCriadoEvent pedidoCriadoEvent = new PedidoCriadoEvent(id, valorTotal);
        rabbitTemplate.convertAndSend("pedido-exchange", "pedido.criado", pedidoCriadoEvent);
    }

}
