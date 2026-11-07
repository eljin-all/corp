package dao;

import models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * DAO-класс для управления пользователями и их ролями в БД PostgreSQL.
 */
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserDao(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Инициализация таблицы пользователей и создание базовых аккаунтов admin и user.
     */
    public void initTable() {
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS public;");
        String sql = "CREATE TABLE IF NOT EXISTS public.users (" +
                "id SERIAL PRIMARY KEY, " +
                "username VARCHAR(100) UNIQUE NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "role VARCHAR(50) NOT NULL, " +
                "enabled BOOLEAN NOT NULL DEFAULT TRUE)";
        jdbcTemplate.execute(sql);

        if (findByUsername("admin") == null) {
            save(new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
        }
        if (findByUsername("user") == null) {
            save(new User("user", passwordEncoder.encode("user"), "ROLE_USER"));
        }
    }

    /**
     * Поиск пользователя по логину.
     */
    public User findByUsername(String username) {
        String sql = "SELECT id, username, password, role, enabled FROM public.users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * Сохранение нового пользователя в базу данных.
     */
    public int save(User user) {
        String sql = "INSERT INTO public.users (username, password, role, enabled) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.isEnabled());
    }
}