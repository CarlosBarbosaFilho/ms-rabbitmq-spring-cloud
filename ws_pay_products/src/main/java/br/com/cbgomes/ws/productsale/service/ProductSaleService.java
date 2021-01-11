package br.com.cbgomes.ws.productsale.service;

import br.com.cbgomes.ws.productsale.entity.data.ProductDto;

import java.util.List;

public interface ProductSaleService {

    public ProductDto create(ProductDto productDto);

    public List<ProductDto> listAll();

    public void remove(Long idProductSale);

    public ProductDto getById(Long idProductSale);

}
