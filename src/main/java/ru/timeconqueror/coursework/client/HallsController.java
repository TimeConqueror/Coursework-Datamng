package ru.timeconqueror.coursework.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.timeconqueror.coursework.model.Hall;
import ru.timeconqueror.coursework.service.CinemaService;
import ru.timeconqueror.coursework.service.HallService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/halls")
public class HallsController {

    private HallService hallService;
    private CinemaService cinemaService;

    @Autowired
    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }

    @Autowired
    public void setCinemaService(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public String init(Model model) {
        model.addAttribute("allHalls", hallService.findAll());
        return "/halls/list";
    }

    @GetMapping(value = "/add")
    public String initAdder(Model model) {
        model.addAttribute("hall", new Hall());
        model.addAttribute("allCinemas", cinemaService.findAll());
        return "halls/add";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("hall") @Valid Hall hall, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allCinemas", cinemaService.findAll());
            return "halls/add";
        }

        return save(hall);
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("hall") @Valid Hall hall, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allCinemas", cinemaService.findAll());
            return "halls/edit";
        }

        return save(hall);
    }

    public String save(Hall hall) {
        hallService.save(hall);
        return "redirect:/halls";
    }

    @GetMapping(value = "/edit")
    public String initEditor(@RequestParam("id") UUID id, Model model) {
        Hall hall = hallService.findById(id).get();
        model.addAttribute("hall", hall);//TODO add present check
        model.addAttribute("allCinemas", cinemaService.findAll());
        return "halls/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id) {
        hallService.delete(hallService.findById(id).get());//TODO add present check
        return "redirect:/halls";
    }
}
