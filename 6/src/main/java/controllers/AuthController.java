package controllers;

import dao.UserDao;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Контроллер для обработки аутентификации и регистрации пользователей.
 */
@Controller
public class AuthController {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Отображение пользовательской страницы входа.
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "registered", required = false) String registered,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Неверный логин или пароль");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Вы успешно вышли из системы");
        }
        if (registered != null) {
            model.addAttribute("registeredMessage", "Регистрация прошла успешно! Войдите в систему.");
        }
        return "login";
    }

    /**
     * Отображение формы регистрации нового пользователя.
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Обработка отправки формы регистрации и сохранение пользователя с ролью ROLE_USER.
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model) {
        if (userDao.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Пользователь с таким логином уже существует");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        userDao.save(user);

        return "redirect:/login?registered=true";
    }
}