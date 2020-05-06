package ru.timeconqueror.coursework.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.timeconqueror.coursework.model.Cinema;
import ru.timeconqueror.coursework.service.CinemaService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/cinemas")
public class CinemasController {

    private CinemaService cinemaService;

    @Autowired
    public void setCinemaService(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public String init(Model model) {
        model.addAttribute("allCinemas", cinemaService.findAll());
        return "/cinemas/list";
    }

    @GetMapping(value = "/add")
    public String initAdder(Model model) {
        model.addAttribute("cinema", new Cinema());
        return "cinemas/one";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("cinema") @Valid Cinema c, BindingResult result) {
        if (result.hasErrors()) {
            return "cinemas/one";
        }

        cinemaService.save(c);
        return "redirect:/cinemas";
    }

    @GetMapping(value = "/edit")
    public String initEditor(@RequestParam("id") UUID id, Model model) {
        Cinema cinema = cinemaService.findById(id).get();
        model.addAttribute("cinema", cinema);//TODO add present check
        return "cinemas/one";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id) {
        cinemaService.delete(cinemaService.findById(id).get());//TODO add present check
        return "redirect:/cinemas";
    }
}
