package br.com.cbgomes.ws.product.resource;

import br.com.cbgomes.ws.product.entity.Product;
import br.com.cbgomes.ws.rabbitmq.message.ProductSendMessage;
import br.com.cbgomes.ws.utils.constants.ConstantsPaginator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.cbgomes.ws.product.entity.dto.ProductDto;
import br.com.cbgomes.ws.product.sevice.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;
    private final PagedResourcesAssembler<ProductDto> assembler;

    @Autowired
    public ProductResource(ProductService productService, PagedResourcesAssembler<ProductDto> assembler){
        this.productService = productService;
        this.assembler = assembler;
    }

    @ApiOperation(value = "Return the product requested")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the product requested"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get(@PathVariable("id") Long id){
       final ProductDto productDto = this.productService.findById(id);
        productDto.add(linkTo(methodOn(ProductResource.class).get(id)).withSelfRel());
        return  ResponseEntity.ok(productDto);
    }

    @ApiOperation(value = "Create product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create new product"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        final ProductDto product = this.productService.save(productDto);
        product.add(linkTo(methodOn(ProductResource.class).get(product.getIdProduct(product))).withSelfRel());
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "Update product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updating new product"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody ProductDto productDto){
        final ProductDto product = this.productService.update(productDto);

        product.add(linkTo(methodOn(ProductResource.class).get(product.getIdProduct(product))).withSelfRel());
        return ResponseEntity.ok(product);
    }



    @ApiOperation(value = "Return all product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a collection of product paginable"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> all(
            @RequestParam(value = "page", defaultValue = ConstantsPaginator.PAGES) int page,
            @RequestParam(value = "limit", defaultValue = ConstantsPaginator.LIMIT) int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction)
    {
        var ordenation = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC :  Sort.Direction.ASC;
        final Pageable pageable = PageRequest.of(page,limit,Sort.by(ordenation,"name"));
        final Page<ProductDto> products = this.productService.findAll(pageable);

        products.stream()
                .forEach(p -> p.add(linkTo(methodOn(ProductResource.class)
                        .get(p.getIdProduct(p))).withSelfRel()));
       final PagedModel<EntityModel<ProductDto>> pagedModel = assembler.toModel(products);

        return ResponseEntity.ok(products);
    }

    @ApiOperation(value = "Remove the product selected")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "No have return, only status code"),
            @ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
            @ApiResponse(code = 403, message = "Sorry, you dont have permission to acess this service ")
    })
    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.productService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
