package br.com.cbgomes.ws.productsale.entity;

import br.com.cbgomes.ws.productsale.entity.data.ProductDto;
import br.com.cbgomes.ws.sale.entity.Sale;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductSale implements Serializable {

    @Id
    private Long id;

    private BigDecimal price;

    private int stock;

    private int code;

    private String name;

    @ManyToOne
    private Sale sale;

    public static ProductSale createProductSale(ProductDto productDto){
        return new ModelMapper().map(productDto, ProductSale.class);
    }

}
