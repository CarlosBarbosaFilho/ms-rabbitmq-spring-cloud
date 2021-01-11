package br.com.cbgomes.ws.productstock.service;

import br.com.cbgomes.ws.productstock.entity.ProductStock;
import br.com.cbgomes.ws.productstock.entity.data.ProductStockDto;
import br.com.cbgomes.ws.productstock.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class ProductStockServiceImpl implements  ProductStockService{

    private final ProductStockRepository productStockRepository;

    @Autowired
    public ProductStockServiceImpl(final ProductStockRepository productStockRepository){
        this.productStockRepository = productStockRepository;
    }

    @Override
    public ProductStockDto create(ProductStockDto productStockDto) {
        return ProductStockDto.createProductStockDto(this.productStockRepository.save(ProductStock.createProductStock(productStockDto)));
    }

    @Override
    public List<ProductStockDto> listAll() {
        return ProductStockDto.listProductsStockDto(this.productStockRepository.findAll());
    }

    @Override
    public void remove(Long idProductSaleDto) {
            this.productStockRepository.deleteById(idProductSaleDto);
    }

    @Override
    public ProductStockDto getById(Long idProductStock) {
        var productStock = this.productStockRepository.findById(idProductStock).orElseThrow(() -> {
            throw new EntityNotFoundException("Entity not found");
        });
        return ProductStockDto.createProductStockDto(productStock);
    }

}
