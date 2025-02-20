package ru.timeconqueror.coursework.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.timeconqueror.coursework.model.Session;
import ru.timeconqueror.coursework.service.FilmService;
import ru.timeconqueror.coursework.service.HallService;
import ru.timeconqueror.coursework.service.SessionService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.UUID;

@Controller
@RequestMapping("/sessions")
public class SessionsController {

    private SessionService sessionService;
    private HallService hallService;
    private FilmService filmService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Autowired
    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String init(Model model) {
        model.addAttribute("allSessions", sessionService.findAll());
        return "/sessions/list";
    }

    @GetMapping(value = "/add")
    public String initAdder(Model model) {
        model.addAttribute("eSession", new Session());
        model.addAttribute("allHalls", hallService.findAll());
        model.addAttribute("allFilms", filmService.findAll());
        return "sessions/add";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("eSession") @Valid Session session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allHalls", hallService.findAll());
            model.addAttribute("allFilms", filmService.findAll());
            return "sessions/add";
        }

        return save(session);
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("eSession") @Valid Session session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allHalls", hallService.findAll());
            model.addAttribute("allFilms", filmService.findAll());
            return "sessions/edit";
        }

        return save(session);
    }

    public String save(Session session) {
        sessionService.save(session);
        return "redirect:/sessions";
    }

    @GetMapping(value = "/edit")
    public String initEditor(@RequestParam("id") UUID id, Model model) {
        Session session = sessionService.findById(id).get();
        model.addAttribute("eSession", session);//TODO add present check
        model.addAttribute("allHalls", hallService.findAll());
        model.addAttribute("allFilms", filmService.findAll());
        return "sessions/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id) {
        sessionService.delete(sessionService.findById(id).get());//TODO add present check
        return "redirect:/sessions";
    }

    @GetMapping("/select")
    public String select(@RequestParam("query") String price, Model model) {
        Iterable<Session> allSessions = Collections.emptyList();
        String title = "";
        if (price.equals("price_greater_200")) {
            allSessions = sessionService.findAllByPriceGreaterThan("200");
            title = "с ценой выше 200";
        } else if (price.equals("cinema_imperia")) {
            allSessions = sessionService.findAllByCinemaName("Империя");
            title = "кинотеатра Империя";
        }

        model.addAttribute("allSessions", allSessions);
        model.addAttribute("title", title);

        return "sessions/select";
    }
}
