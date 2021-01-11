package br.com.cbgomes.ws.sale.resource;

import br.com.cbgomes.ws.productsale.service.ProductSaleService;
import br.com.cbgomes.ws.sale.entity.data.SaleDto;
import br.com.cbgomes.ws.sale.service.SaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/sales")
public class SaleResourceController {

    private final SaleService service;
    private final ProductSaleService productSaleService;

    @Autowired
    public SaleResourceController(final SaleService saleService, final ProductSaleService productSaleService){
        this.service = saleService;
        this.productSaleService = productSaleService;
    }

    @ApiOperation (value = "Return the sale created")
    @ApiResponses (value = {
            @ApiResponse(code = 200, message = "Return the sale requested"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @PostMapping
    @ResponseStatus( HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody SaleDto saleDto){
        var dto = this.service.create(saleDto);
        dto.add(linkTo(methodOn(SaleResourceController.class).get(dto.getId())).withSelfRel());
        return ResponseEntity.ok(dto);
    }

    @ApiOperation (value = "Return the sale requested")
    @ApiResponses (value = {
            @ApiResponse(code = 200, message = "Return the sale requested"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        var sale = this.service.getById(id);
        sale.add(linkTo(methodOn(SaleResourceController.class).get(sale.getId())).withSelfRel());
        return ResponseEntity.ok(sale);
    }

    @ApiOperation (value = "Return all sales saved")
    @ApiResponses (value = {
            @ApiResponse(code = 200, message = "Return the product requested"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> list(){
        var sales = this.service.listAll();
        sales.stream().forEach(s -> {
            s.add(linkTo(methodOn(SaleResourceController.class).get(s.getId())).withSelfRel());
        });
        return ResponseEntity.ok(sales);
    }

    @ApiOperation (value = "Not return")
    @ApiResponses (value = {
            @ApiResponse(code = 200, message = "Return empty"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id){
        this.service.remove(id);
    }

}
