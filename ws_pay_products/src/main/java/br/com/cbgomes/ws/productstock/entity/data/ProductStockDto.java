package br.com.cbgomes.ws.productstock.entity.data;

import br.com.cbgomes.ws.productstock.entity.ProductStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class  ProductStockDto extends RepresentationModel<ProductStockDto> implements Serializable {

    private Long id;
    private int stock;

    public static ProductStockDto createProductStockDto(ProductStock productStock){
        return new ModelMapper().map(productStock, ProductStockDto.class);
    }

    public static List<ProductStockDto> listProductsStockDto(List<ProductStock> products){
        return products.stream()
                .map(product -> createProductStockDto(product)).collect(Collectors.toList());
    }

    public static Long getIdProductStock(ProductStockDto productStockDto){
        return productStockDto.id;
    }
}
