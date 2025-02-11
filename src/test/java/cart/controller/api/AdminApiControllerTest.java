package cart.controller.api;

import cart.controller.dto.request.ProductCreateRequest;
import cart.controller.dto.request.ProductUpdateRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminApiControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("상품 추가")
    @Test
    void createProduct() {
        ProductCreateRequest request = new ProductCreateRequest("product", 1000, "url");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/admin/product")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("상품 수정")
    @Test
    void updateProduct() {
        Long id = 1L;
        ProductUpdateRequest request = new ProductUpdateRequest("product", 1000, "url");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().put("/admin/product/" + id)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("상품 삭제")
    @Test
    void deleteProduct() {
        Long id = 1L;

        RestAssured.given().log().all()
                .when().delete("/admin/product/" + id)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
