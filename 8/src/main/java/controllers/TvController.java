package controllers;

import dao.TvDao;
import jms.Alert;
import jms.AlertService;
import models.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер для управления каталогом телевизоров и отправки JMS-уведомлений.
 */
@Controller
@RequestMapping("/tvs")
public class TvController {

    private final TvDao tvDao;
    private final AlertService alertService;

    @Autowired
    public TvController(TvDao tvDao, AlertService alertService) {
        this.tvDao = tvDao;
        this.alertService = alertService;
    }

    /**
     * Отображение списка доступных (не купленных) телевизоров.
     */
    @GetMapping
    public String listTvs(@RequestParam(value = "bought", required = false) String bought, Model model) {
        if (bought != null) {
            model.addAttribute("successMessage", "Заявка на покупку отправлена в брокер сообщений! Товар помечен как купленный.");
        }
        model.addAttribute("tvs", tvDao.findAll());
        return "tv_list";
    }

    /**
     * Форма добавления нового телевизора.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tv", new Tv());
        return "tv_add";
    }

    /**
     * Обработка добавления телевизора + отправка уведомления в очередь (п. 2).
     */
    @PostMapping("/add")
    public String addTv(@Valid @ModelAttribute("tv") Tv tv, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tv_add";
        }
        tvDao.insert(tv);
        alertService.sendAlert(new Alert("ДОБАВЛЕНИЕ", "Добавлен новый телевизор в каталог", tv));
        return "redirect:/tvs";
    }

    /**
     * Форма редактирования телевизора.
     */
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_edit";
    }

    /**
     * Обработка редактирования телевизора + отправка уведомления в очередь (п. 2).
     */
    @PostMapping("/edit")
    public String updateTv(@Valid @ModelAttribute("tv") Tv tv, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tv_edit";
        }
        tvDao.update(tv);
        alertService.sendAlert(new Alert("РЕДАКТИРОВАНИЕ", "Изменены параметры телевизора ID=" + tv.getId(), tv));
        return "redirect:/tvs";
    }

    /**
     * Страница подтверждения удаления телевизора.
     */
    @GetMapping("/delete")
    public String showDeletePage(@RequestParam("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_delete";
    }

    /**
     * Обработка удаления телевизора + отправка уведомления в очередь (п. 2).
     */
    @PostMapping("/delete")
    public String deleteTv(@RequestParam("id") int id) {
        Tv tv = tvDao.findById(id);
        if (tv != null) {
            tvDao.delete(id);
            alertService.sendAlert(new Alert("УДАЛЕНИЕ", "Удален телевизор из каталога ID=" + id, tv));
        }
        return "redirect:/tvs";
    }

    /**
     * Покупка телевизора: отправка сообщения в брокер и скрытие купленного товара (п. 4 и 5*).
     */
    @PostMapping("/buy")
    public String buyTv(@RequestParam("id") int id) {
        Tv tv = tvDao.findById(id);
        if (tv != null && !tv.isSold()) {
            // Сначала отправляем информацию о покупаемом товаре в очередь брокера
            alertService.sendAlert(new Alert("ПОКУПКА", "Пользователь оформил покупку телевизора", tv));
            // Помечаем товар как купленный в БД
            tvDao.markAsSold(id);
        }
        return "redirect:/tvs?bought=true";
    }

    /**
     * Поиск телевизоров по максимальной цене.
     */
    @GetMapping("/search")
    public String searchTvs(@RequestParam(value = "maxPrice", required = false) Double maxPrice, Model model) {
        if (maxPrice != null) {
            model.addAttribute("tvs", tvDao.findByPriceLessThanEqual(maxPrice));
            model.addAttribute("maxPrice", maxPrice);
        }
        return "tv_search";
    }
}