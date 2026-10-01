import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TvDao {

    private final JdbcTemplate jdbcTemplate;

    public TvDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tv> findAll() {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price FROM tv ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class));
    }

    public Tv findById(int id) {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price FROM tv WHERE id = ?";
        List<Tv> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class), id);
        return results.isEmpty() ? null : results.get(0);
    }

    public int insert(Tv tv) {
        String sql = "INSERT INTO tv (brand, model, screen_technology, screen_diagonal, price) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                tv.getBrand(),
                tv.getModel(),
                tv.getScreenTechnology(),
                tv.getScreenDiagonal(),
                tv.getPrice());
    }

    public int update(Tv tv) {
        String sql = "UPDATE tv SET brand = ?, model = ?, screen_technology = ?, screen_diagonal = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                tv.getBrand(),
                tv.getModel(),
                tv.getScreenTechnology(),
                tv.getScreenDiagonal(),
                tv.getPrice(),
                tv.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM tv WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Tv> findByPriceLessThanEqual(double maxPrice) {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price FROM tv WHERE price <= ? ORDER BY price";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class), maxPrice);
    }
}