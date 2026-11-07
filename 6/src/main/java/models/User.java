package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Модель учетной записи пользователя приложения.
 */
public class User {
    private Integer id;

    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 3, max = 50, message = "Логин должен содержать от 3 до 50 символов")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    private String password;

    private String role;
    private boolean enabled = true;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}