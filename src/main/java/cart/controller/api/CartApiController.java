package cart.controller.api;

import cart.annotation.Authenticate;
import cart.controller.dto.request.ProductIdRequest;
import cart.controller.dto.response.CartItemResponse;
import cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartApiController {

    private final CartService cartService;

    public CartApiController(final CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/product")
    public ResponseEntity<Void> save(
            @Authenticate final Long memberId,
            @RequestBody @Valid final ProductIdRequest productIdRequest
    ) {
        cartService.save(memberId, productIdRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<CartItemResponse>> findAll(@Authenticate final Long memberId) {
        List<CartItemResponse> responses = cartService.findAll(memberId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        cartService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
