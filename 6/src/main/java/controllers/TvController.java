package controllers;

import dao.TvDao;
import models.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tvs")
public class TvController {

    private final TvDao tvDao;

    @Autowired
    public TvController(TvDao tvDao) {
        this.tvDao = tvDao;
    }

    // 2. Страница просмотра таблицы записей
    @GetMapping
    public String listTvs(Model model) {
        model.addAttribute("tvs", tvDao.findAll());
        return "tv_list";
    }

    // 3. Форма добавления новой записи
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tv", new Tv());
        return "tv_add";
    }

    @PostMapping("/add")
    public String addTv(@Valid @ModelAttribute("tv") Tv tv, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tv_add";
        }
        tvDao.insert(tv);
        return "redirect:/tvs";
    }

    // 4. Форма редактирования записи
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_edit";
    }

    @PostMapping("/edit")
    public String updateTv(@Valid @ModelAttribute("tv") Tv tv, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tv_edit";
        }
        tvDao.update(tv);
        return "redirect:/tvs";
    }

    // 5. Страница удаления записи с подтверждением
    @GetMapping("/delete")
    public String showDeletePage(@RequestParam("id") int id, Model model) {
        Tv tv = tvDao.findById(id);
        if (tv == null) {
            return "redirect:/tvs";
        }
        model.addAttribute("tv", tv);
        return "tv_delete";
    }

    @PostMapping("/delete")
    public String deleteTv(@RequestParam("id") int id) {
        tvDao.delete(id);
        return "redirect:/tvs";
    }

    // 6. Страница просмотра записей по критерию (цена не выше заданной)
    @GetMapping("/search")
    public String searchTvs(@RequestParam(value = "maxPrice", required = false) Double maxPrice, Model model) {
        if (maxPrice != null) {
            model.addAttribute("tvs", tvDao.findByPriceLessThanEqual(maxPrice));
            model.addAttribute("maxPrice", maxPrice);
        }
        return "tv_search";
    }
}