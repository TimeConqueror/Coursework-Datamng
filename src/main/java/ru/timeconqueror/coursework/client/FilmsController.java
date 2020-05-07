package ru.timeconqueror.coursework.client;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.timeconqueror.coursework.model.Film;
import ru.timeconqueror.coursework.service.FilmService;
import ru.timeconqueror.coursework.util.FileContainer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/films")
public class FilmsController {

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String init(Model model) {
        model.addAttribute("allFilms", filmService.findAll());
        return "/films/list";
    }

    @GetMapping(value = "/add")
    public String initAdder(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("fileContainer", new FileContainer());
        return "films/add";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("film") @Valid Film film, BindingResult result, @ModelAttribute("fileContainer") FileContainer fileContainer) throws IOException {
        if (result.hasErrors()) {
            return "films/add";
        }

        return save(film, fileContainer);
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("film") @Valid Film film, BindingResult result, @ModelAttribute("fileContainer") FileContainer fileContainer) throws IOException {
        if (result.hasErrors()) {
            return "films/edit";
        }

        return save(film, fileContainer);
    }

    public String save(Film film, FileContainer fileContainer) throws IOException {
        if (fileContainer.getFileData().getSize() > 0) {
            film.setLogo(fileContainer.getFileData().getBytes());
        } else {
            Optional<Film> filmOpt = filmService.findById(film.getId());
            filmOpt.ifPresent(film1 -> film.setLogo(film1.getLogo()));
        }

        filmService.save(film);
        return "redirect:/films";
    }

    @GetMapping(value = "/edit")
    public String initEditor(@RequestParam("id") UUID id, Model model) {
        Film film = filmService.findById(id).get();
        model.addAttribute("film", film);//TODO add present check
        model.addAttribute("fileContainer", new FileContainer());
        return "films/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id) {
        filmService.delete(filmService.findById(id).get());//TODO add present check
        return "redirect:/films";
    }

    @GetMapping("/images")
    public void showFilmImage(@RequestParam("id") UUID id, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        Film film = filmService.findById(id).get();//TODO add present check

        InputStream is = new ByteArrayInputStream(film.getLogo());
        IOUtils.copy(is, response.getOutputStream());
    }
}
