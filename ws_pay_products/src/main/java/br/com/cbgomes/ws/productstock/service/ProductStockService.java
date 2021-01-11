package br.com.cbgomes.ws.productstock.service;

import br.com.cbgomes.ws.productstock.entity.data.ProductStockDto;

import java.util.List;

public interface ProductStockService {

    public ProductStockDto create(ProductStockDto productStockDto);

    public List<ProductStockDto> listAll();

    public void remove(Long idProductStock);

    public ProductStockDto getById(Long idProductStock);
}
