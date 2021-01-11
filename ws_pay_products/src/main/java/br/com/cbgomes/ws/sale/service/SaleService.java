package br.com.cbgomes.ws.sale.service;

import br.com.cbgomes.ws.sale.entity.Sale;
import br.com.cbgomes.ws.sale.entity.data.SaleDto;

import java.util.List;

public interface SaleService {

    public SaleDto create(SaleDto sale);

    public List<SaleDto> listAll();

    public void remove(Long idSale);

    public SaleDto getById(Long idSale);
}
