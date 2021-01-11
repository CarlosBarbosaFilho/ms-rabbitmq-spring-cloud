package br.com.cbgomes.ws.productstock.entity;

import br.com.cbgomes.ws.productstock.entity.data.ProductStockDto;
import br.com.cbgomes.ws.sale.entity.Sale;
import br.com.cbgomes.ws.sale.entity.data.SaleDto;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductStock implements Serializable {

    @Id
    private Long id;

    private int stock;

    public static ProductStock createProductStock(ProductStockDto productStockDto){
        return new ModelMapper().map(productStockDto, ProductStock.class);
    }
}
