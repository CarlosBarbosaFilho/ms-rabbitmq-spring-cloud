package br.com.cbgomes.ws.rabbitmq.message;

import br.com.cbgomes.ws.product.entity.dto.ProductDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductSendMessage {

    @Value ("${ws.product.rabbitmq.exchange}")
    private String exchange;

    @Value("${ws.product.rabbitmq.routingkey}")
    private String routingkey;

    private final RabbitTemplate template;

    @Autowired
    public ProductSendMessage(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(ProductDto productDto){
        template.convertAndSend(exchange,routingkey,productDto);
    }
}
