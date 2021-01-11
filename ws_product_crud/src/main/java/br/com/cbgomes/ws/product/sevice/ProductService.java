package br.com.cbgomes.ws.product.sevice;

import br.com.cbgomes.ws.product.entity.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public ProductDto save(ProductDto productDto);

    public List<ProductDto> list();

    public Page<ProductDto> findAll(Pageable pageable);

    public ProductDto findById(Long id);

    public void remove(Long id);

    public ProductDto update(ProductDto productDto);
}
