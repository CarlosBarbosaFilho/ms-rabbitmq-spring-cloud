package br.com.cbgomes.ws.product.sevice;

import br.com.cbgomes.ws.exception.EntityNotFoudException;
import br.com.cbgomes.ws.product.entity.Product;
import br.com.cbgomes.ws.product.entity.dto.ProductDto;
import br.com.cbgomes.ws.product.infrastructure.ProductRepository;
import br.com.cbgomes.ws.rabbitmq.message.ProductSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductSendMessage productSendMessage;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, ProductSendMessage productSendMessage) {
        this.productRepository = productRepository;
        this.productSendMessage = productSendMessage;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        ProductDto dto = ProductDto.createProductDto(this.productRepository.save(Product.createProduc(productDto)));
        this.productSendMessage.sendMessage(dto);
        return dto;
    }

    @Override
    public List<ProductDto> list() {
        return ProductDto.listProductsDto(this.productRepository.findAll());

    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        var page = this.productRepository.findAll(pageable);
        return page.map(this::generateProductDto);
    }

    @Override
    public ProductDto findById(Long id) {
        return ProductDto.createProductDto(this.productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoudException("Produc not fond")
        ));
    }

    @Override
    public void remove(Long id) {
        this.productRepository.delete(this.productRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoudException("Entity not found to remove");
        }));
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        final Optional<Product> product = this.productRepository.findById(productDto.getIdProduct(productDto));
        if(!product.isPresent()){
            throw new EntityNotFoudException("Entity not found to update");
        }
        return ProductDto.createProductDto(this.productRepository.save(Product.createProduc(productDto)));
    }

    private ProductDto generateProductDto(Product product) {
        return ProductDto.createProductDto(product);
    }
}
