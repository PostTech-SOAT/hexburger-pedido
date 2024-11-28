package br.com.hexburger.pedido.framework.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue pagamentoQueue() {
        return new Queue("pagamento-queue", true);
    }

    @Bean
    public Queue pedidoQueue() {
        return new Queue("pedido-queue", true);
    }

    @Bean
    public TopicExchange pedidoExchange() {
        return new TopicExchange("pedido-exchange");
    }

    @Bean
    public Binding pedidoBinding(Queue pedidoQueue, TopicExchange pedidoExchange) {
        return BindingBuilder.bind(pedidoQueue).to(pedidoExchange).with("pedido.#");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
