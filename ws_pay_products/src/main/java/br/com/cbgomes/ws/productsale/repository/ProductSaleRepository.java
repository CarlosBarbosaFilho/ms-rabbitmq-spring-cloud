package br.com.cbgomes.ws.productsale.repository;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {


}
