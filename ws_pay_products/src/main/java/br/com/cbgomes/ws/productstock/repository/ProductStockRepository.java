package br.com.cbgomes.ws.productstock.repository;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.productstock.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}
