package br.com.cbgomes.ws.sale.entity;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.sale.entity.data.SaleDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDate date_created;

    private BigDecimal value;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.REFRESH)
    private List<ProductSale> productsSale;

    public static Sale createSale(SaleDto saleDto){
        return new ModelMapper().map(saleDto, Sale.class);
    }
}
