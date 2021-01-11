package br.com.cbgomes.ws.sale.entity.data;

import br.com.cbgomes.ws.sale.entity.Sale;
import br.com.cbgomes.ws.productsale.entity.data.ProductDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL)
public class SaleDto extends RepresentationModel<SaleDto> implements Serializable {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_created;

    private List<ProductDto> productsSaleDto;

    private BigDecimal totalSales;

    public static SaleDto createSaleDto(Sale sale){
        return new ModelMapper().map(sale, SaleDto.class);
    }

    public static SaleDto createProductSaleDto(Sale sale){
        return new ModelMapper().map(sale, SaleDto.class);
    }

    public static List<SaleDto> listSaleDto(List<Sale> sales){
        return sales.stream()
                .map(sale -> createSaleDto(sale))
                .collect(Collectors.toList());
    }

    public static BigDecimal getTotalAmount(List<ProductDto> productDtos){
       return  productDtos.stream()
                .map(ProductDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Long getIdSale(SaleDto saleDto){
        return saleDto.id;
    }
}
