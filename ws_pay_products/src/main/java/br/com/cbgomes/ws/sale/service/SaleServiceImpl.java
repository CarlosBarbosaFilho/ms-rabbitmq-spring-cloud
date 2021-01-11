package br.com.cbgomes.ws.sale.service;

import br.com.cbgomes.ws.productsale.entity.ProductSale;
import br.com.cbgomes.ws.productsale.entity.data.ProductDto;
import br.com.cbgomes.ws.productsale.repository.ProductSaleRepository;
import br.com.cbgomes.ws.sale.entity.Sale;
import br.com.cbgomes.ws.sale.entity.data.SaleDto;
import br.com.cbgomes.ws.sale.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements  SaleService{

    private final SaleRepository repository;
    private final ProductSaleRepository productSaleRepository;

    @Autowired
    public SaleServiceImpl(final SaleRepository repository, ProductSaleRepository productSaleRepository){
        this.repository = repository;
        this.productSaleRepository = productSaleRepository;
    }

    @Override
    @Transactional
    public SaleDto create(SaleDto saleDto) {
        var saleBase = this.repository.save(Sale.createSale(saleDto));
        buildDtoReturn(saleDto, saleBase);
        return saleDto;
    }

    @Override
    public List<SaleDto> listAll() {
       List<SaleDto> dtos = new ArrayList<>();
        this.repository.findAll().stream().forEach(s -> {
            var sdto = SaleDto.createSaleDto(s);
            var productsdtos = ProductDto.listProductsDto(s.getProductsSale());
            sdto.setTotalSales(sdto.getTotalAmount(productsdtos));
            sdto.setProductsSaleDto(productsdtos);
            dtos.add(sdto);
        });
        return dtos;
    }

    @Override
    public void remove(Long idSale) {
        this.repository.deleteById(idSale);
    }

    @Override
    public SaleDto getById(Long idSale) {
        var sale = this.repository.findById(idSale).orElseThrow(() ->{
            throw new EntityNotFoundException("Entity not found exception");
        });
        return SaleDto.createSaleDto(sale);

    }

    private void buildDtoReturn(SaleDto saleDto, Sale saleBase) {
        saleDto.setTotalSales(SaleDto.getTotalAmount(saleDto.getProductsSaleDto()));
        saleDto.setId(saleBase.getId());
        saleDto.getProductsSaleDto().forEach(p -> {
            ProductSale ps = ProductSale.createProductSale(p);
            saleBase.getProductsSale().add(this.productSaleRepository.save(ps));
        });
    }
}
