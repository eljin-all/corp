package dao;

import models.Tv;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * DAO-класс для работы с таблицей public.tv в PostgreSQL.
 */
public class TvDao {

    private final JdbcTemplate jdbcTemplate;

    public TvDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Инициализация схемы и таблицы БД (включая столбец sold для учета купленных товаров).
     */
    public void initTable() {
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS public;");
        String createTableSql = "CREATE TABLE IF NOT EXISTS public.tv (" +
                "id SERIAL PRIMARY KEY, " +
                "brand VARCHAR(100) NOT NULL, " +
                "model VARCHAR(100) NOT NULL, " +
                "screen_technology VARCHAR(100) NOT NULL, " +
                "screen_diagonal DOUBLE PRECISION NOT NULL, " +
                "price DOUBLE PRECISION NOT NULL, " +
                "sold BOOLEAN NOT NULL DEFAULT FALSE)";
        jdbcTemplate.execute(createTableSql);

        // Гарантируем наличие столбца sold, если таблица осталась с 5-й работы
        jdbcTemplate.execute("ALTER TABLE public.tv ADD COLUMN IF NOT EXISTS sold BOOLEAN NOT NULL DEFAULT FALSE;");

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.tv WHERE sold = FALSE", Integer.class);
        if (count != null && count == 0) {
            jdbcTemplate.update("INSERT INTO public.tv (brand, model, screen_technology, screen_diagonal, price, sold) VALUES " +
                    "('Samsung', 'QE55Q60A', 'QLED', 55.0, 65000.0, FALSE), " +
                    "('LG', 'OLED55C2', 'OLED', 55.0, 115000.0, FALSE), " +
                    "('Sony', 'KD-43X81J', 'LED', 43.0, 48000.0, FALSE)");
        }
    }

    /**
     * Выборка всех доступных к покупке (не купленных) телевизоров.
     */
    public List<Tv> findAll() {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price, sold " +
                     "FROM public.tv WHERE sold = FALSE ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class));
    }

    /**
     * Поиск телевизора по первичному ключу.
     */
    public Tv findById(int id) {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price, sold " +
                     "FROM public.tv WHERE id = ?";
        List<Tv> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class), id);
        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Добавление новой записи с присвоением сгенерированного ID.
     */
    public int insert(Tv tv) {
        String sql = "INSERT INTO public.tv (brand, model, screen_technology, screen_diagonal, price, sold) " +
                     "VALUES (?, ?, ?, ?, ?, FALSE) RETURNING id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, Integer.class,
                tv.getBrand(),
                tv.getModel(),
                tv.getScreenTechnology(),
                tv.getScreenDiagonal(),
                tv.getPrice());
        if (generatedId != null) {
            tv.setId(generatedId);
            return 1;
        }
        return 0;
    }

    /**
     * Обновление данных существующего телевизора.
     */
    public int update(Tv tv) {
        String sql = "UPDATE public.tv SET brand = ?, model = ?, screen_technology = ?, " +
                     "screen_diagonal = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                tv.getBrand(),
                tv.getModel(),
                tv.getScreenTechnology(),
                tv.getScreenDiagonal(),
                tv.getPrice(),
                tv.getId());
    }

    /**
     * Удаление записи по ID.
     */
    public int delete(int id) {
        String sql = "DELETE FROM public.tv WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * Пометка товара как купленного (пункт 5* задания).
     */
    public int markAsSold(int id) {
        String sql = "UPDATE public.tv SET sold = TRUE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * Поиск непроданных телевизоров по максимальной цене.
     */
    public List<Tv> findByPriceLessThanEqual(double maxPrice) {
        String sql = "SELECT id, brand, model, screen_technology, screen_diagonal, price, sold " +
                     "FROM public.tv WHERE sold = FALSE AND price <= ? ORDER BY price";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tv.class), maxPrice);
    }
}