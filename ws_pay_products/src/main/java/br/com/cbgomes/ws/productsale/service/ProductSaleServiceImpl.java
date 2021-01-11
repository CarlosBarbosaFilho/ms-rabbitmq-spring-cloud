package br.com.cbgomes.ws.productsale.service;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.productsale.entity.data.ProductDto;
import br.com.cbgomes.ws.productsale.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductSaleServiceImpl implements ProductSaleService {

    private final ProductSaleRepository productSaleRepository;

    @Autowired
    public ProductSaleServiceImpl(final ProductSaleRepository productSaleRepository){
        this.productSaleRepository = productSaleRepository;
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto productDto) {
        var sale = this.productSaleRepository.save(ProductSale.createProductSale(productDto));
        return ProductDto.createProductSaleDto(sale);
    }

    @Override
    public List<ProductDto> listAll() {
        return ProductDto.listProductsDto(this.productSaleRepository.findAll());
    }

    @Override
    public void remove(Long idProductSale) {
        this.productSaleRepository.deleteById(idProductSale);
    }

    @Override
    public ProductDto getById(Long idProductSale) {
        var productSale = this.productSaleRepository.findById(idProductSale).orElseThrow(() -> {
           throw new EntityNotFoundException("Entity not found ");
        });
        return ProductDto.createProductSaleDto(productSale);
    }

}
