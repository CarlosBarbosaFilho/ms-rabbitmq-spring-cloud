package br.com.cbgomes.ws.product.entity.dto;

import br.com.cbgomes.ws.product.entity.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProductDto extends RepresentationModel<ProductDto> implements Serializable {

    private Long id;
    private BigDecimal price;
    private int stock;
    private int code;
    private String name;

    public static ProductDto createProductDto(Product product){
        return new ModelMapper().map(product, ProductDto.class);
    }

    public static List<ProductDto> listProductsDto(List<Product> products){
        return products.stream()
                .map(product -> createProductDto(product)).collect(Collectors.toList());
    }

    public static Long getIdProduct(ProductDto productDto){
        return productDto.id;
    }

}
