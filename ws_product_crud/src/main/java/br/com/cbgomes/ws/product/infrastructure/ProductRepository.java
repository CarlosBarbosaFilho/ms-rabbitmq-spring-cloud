package br.com.cbgomes.ws.product.infrastructure;

import br.com.cbgomes.ws.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
