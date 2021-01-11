package br.com.cbgomes.ws.rabbitmq.consumer;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.productsale.entity.data.ProductDto;
import br.com.cbgomes.ws.productstock.entity.ProductStock;
import br.com.cbgomes.ws.productstock.entity.data.ProductStockDto;
import br.com.cbgomes.ws.productstock.repository.ProductStockRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductListenerMessage {
    private final ProductStockRepository productSaleRepository;

    @Autowired
    public ProductListenerMessage(ProductStockRepository productSaleRepository) {
        this.productSaleRepository = productSaleRepository;
    }

    @RabbitListener(queues = {"${ws.product.rabbitmq.queue}"})
    public void listenerMessage(@Payload ProductStockDto productStockDto){
        this.productSaleRepository.save(ProductStock.createProductStock(productStockDto));
    }
}
