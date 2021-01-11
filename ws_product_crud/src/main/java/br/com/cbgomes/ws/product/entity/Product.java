package br.com.cbgomes.ws.product.entity;

import br.com.cbgomes.ws.product.entity.dto.ProductDto;
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
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private int stock;
    private int code;;
    private String name;

    public static Product createProduc(ProductDto productDto){
        return new ModelMapper().map(productDto, Product.class);
    }
}
