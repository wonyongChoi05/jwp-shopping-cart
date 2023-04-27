package cart.dao;

import cart.entity.ProductEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(final ProductEntity product) {
        String sql = "INSERT INTO PRODUCT (name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<ProductEntity> findAll() {
        String sql = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(sql, (rs, rowNum)
                -> new ProductEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("image_url"),
                rs.getInt("price"))
        );
    }

    public void updateById(final Long id, final ProductEntity product) {
        String sql = "UPDATE PRODUCT SET(name, price, image_url) = (?, ?, ?) WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    public void deleteById(final Long id) {
        String sql = "DELETE FROM PRODUCT WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
