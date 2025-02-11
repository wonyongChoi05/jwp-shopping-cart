package cart.controller.api;

import cart.controller.dto.request.ProductCreateRequest;
import cart.controller.dto.request.ProductUpdateRequest;
import cart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/products")
public class AdminApiController {

    private final ProductService productService;

    public AdminApiController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final ProductCreateRequest request) {
        productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable final Long id,
            @RequestBody @Valid final ProductUpdateRequest request
    ) {
        productService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable final Long id
    ) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
