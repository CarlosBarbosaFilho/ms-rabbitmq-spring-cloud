package br.com.cbgomes.ws.productsale.entity.data;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.sale.entity.data.SaleDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL)
public class ProductDto implements Serializable {

    private Long id;

    private BigDecimal price;

    private int stock;

    private int code;

    private String name;

    private BigDecimal totalAmount;


    public static ProductDto createProductSaleDto(ProductSale productSale){
        return new ModelMapper().map(productSale, ProductDto.class);
    }

    public static List<ProductDto> listProductsDto(List<ProductSale> productsSeles){
        return productsSeles.stream()
                .map(productSale -> createProductSaleDto(productSale)).collect(Collectors.toList());
    }

    public static BigDecimal getTotalAmount(List<ProductDto> productDtos){
        final BigDecimal totalSum = productDtos.stream()
                .map(ProductDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalSum;
    }

    public static Long getIdProductSale(ProductDto productDto){
        return productDto.id;
    }
}
