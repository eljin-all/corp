import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TvDao {

    private final JdbcTemplate jdbcTemplate;

    public TvDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initTable() {
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS public;");
        String createTableSql = "CREATE TABLE IF NOT EXISTS public.tv (" +
                "id SERIAL PRIMARY KEY, " +
                "brand VARCHAR(100) NOT NULL, " +
                "model VARCHAR(100) NOT NULL, " +
                "screen_technology VARCHAR(100) NOT NULL, " +
                "screen_diagonal DOUBLE PRECISION NOT NULL, " +
                "price DOUBLE PRECISION NOT NULL)";
        jdbcTemplate.execute(createTableSql);

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.tv", Integer.class);
        if (count != null && count == 0) {
            jdbcTemplate.update("INSERT INTO public.tv (brand, model, screen_technology, screen_diagonal, price) VALUES " +
                    "('Samsung', 'QE55Q60A', 'QLED', 55.0, 65000.0), " +
                    "('LG', 'OLED55C2', 'OLED', 55.0, 115000.0), " +
                    "('Sony', 'KD-43X81J', 'LED', 43.0, 48000.0)");
        }
    }

    public List<Tv> findAll() {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price FROM public.tv ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class));
    }

    public Tv findById(int id) {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price FROM public.tv WHERE id = ?";
        List<Tv> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class), id);
        return results.isEmpty() ? null : results.get(0);
    }

    public int insert(Tv tv) {
        String sql = "INSERT INTO public.tv (brand, model, screen_technology, screen_diagonal, price) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                tv.getBrand(),
                tv.getModel(),
                tv.getScreenTechnology(),
                tv.getScreenDiagonal(),
                tv.getPrice());
    }

    public int update(Tv tv) {
        String sql = "UPDATE public.tv SET brand = ?, model = ?, screen_technology = ?, screen_diagonal = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                tv.getBrand(),
                tv.getModel(),
                tv.getScreenTechnology(),
                tv.getScreenDiagonal(),
                tv.getPrice(),
                tv.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM public.tv WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Tv> findByPriceLessThanEqual(double maxPrice) {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price FROM public.tv WHERE price <= ? ORDER BY price";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class), maxPrice);
    }
}