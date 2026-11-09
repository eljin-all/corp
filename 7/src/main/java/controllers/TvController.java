package controllers;

import dao.TvDao;
import models.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер управления ресурсами Tv с поддержкой архитектуры REST (HTML и JSON).
 */
@Controller
@RequestMapping("/tvs")
public class TvController {

    private final TvDao tvDao;

    @Autowired
    public TvController(TvDao tvDao) {
        this.tvDao = tvDao;
    }

    // =========================================================================
    // 1. REST API МЕТОДЫ (JSON: GET, POST, PUT, DELETE)
    // =========================================================================

    /**
     * GET /tvs (JSON) — извлечение списка всех телевизоров в формате JSON.
     */
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public List<Tv> getAllTvsJson() {
        return tvDao.findAll();
    }

    /**
     * GET /tvs/{id} (JSON) — извлечение одной записи по ID в формате JSON.
     */
    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public Tv getTvByIdJson(@PathVariable("id") int id) {
        return tvDao.findById(id);
    }

    /**
     * POST /tvs (JSON) — добавление новой записи через REST-клиент.
     */
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Tv createTvJson(@RequestBody Tv tv) {
        tvDao.insert(tv);
        return tv;
    }

    /**
     * PUT /tvs/{id} (JSON) — редактирование существующей записи через REST-клиент.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTvJson(@PathVariable("id") int id, @RequestBody Tv tv) {
        tv.setId(id);
        tvDao.update(tv);
    }

    /**
     * DELETE /tvs/{id} — удаление записи по ID через REST-клиент.
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTvJson(@PathVariable("id") int id) {
        tvDao.delete(id);
    }

    // =========================================================================
    // 2. ВЕБ-ИНТЕРФЕЙС (HTML-ПРЕДСТАВЛЕНИЯ FREEMARKER В СТИЛЕ REST)
    // =========================================================================

    /**
     * GET /tvs (HTML) — просмотр таблицы всех записей.
     */
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String listTvsHtml(Model model) {
        model.addAttribute("tvs", tvDao.findAll());
        return "tv_list";
    }

    /**
     * GET /tvs/{id} (HTML) — просмотр страницы одного телевизора.
     */
    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String showTvHtml(@PathVariable("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_show";
    }

    /**
     * GET /tvs/add — форма добавления новой записи.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tv", new Tv());
        return "tv_add";
    }

    /**
     * POST /tvs/add — обработка HTML-формы добавления записи с валидацией.
     */
    @PostMapping("/add")
    public String addTvFromForm(@Valid @ModelAttribute("tv") Tv tv, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tv_add";
        }
        tvDao.insert(tv);
        return "redirect:/tvs";
    }

    /**
     * GET /tvs/{id}/edit — форма редактирования записи (RESTful URL без ?id=...).
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_edit";
    }

    /**
     * POST /tvs/{id}/edit — сохранение изменений из HTML-формы редактирования.
     */
    @PostMapping("/{id}/edit")
    public String updateTvFromForm(@PathVariable("id") int id,
                                   @Valid @ModelAttribute("tv") Tv tv,
                                   BindingResult bindingResult) {
        tv.setId(id);
        if (bindingResult.hasErrors()) {
            return "tv_edit";
        }
        tvDao.update(tv);
        return "redirect:/tvs";
    }

    /**
     * GET /tvs/{id}/delete — страница подтверждения удаления (RESTful URL).
     */
    @GetMapping("/{id}/delete")
    public String showDeletePage(@PathVariable("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_delete";
    }

    /**
     * POST /tvs/{id}/delete — выполнение удаления из HTML-формы.
     */
    @PostMapping("/{id}/delete")
    public String deleteTvFromForm(@PathVariable("id") int id) {
        tvDao.delete(id);
        return "redirect:/tvs";
    }

    /**
     * GET /tvs/search — страница поиска телевизоров по максимальной цене.
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