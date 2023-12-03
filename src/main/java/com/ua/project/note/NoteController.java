package com.ua.project.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService service;

    @GetMapping("/list")
    public ModelAndView listNotes() {
        ModelAndView result = new ModelAndView("note/list");
        result.addObject("notes", service.listAll());
        return result;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("id") long id) {
        service.deleteById(id);
        return new ModelAndView("redirect:list");
    }

    @GetMapping("/edit")
    public ModelAndView getEditNote(@RequestParam("id") long id) {
        ModelAndView result = new ModelAndView("note/edit");
        Note note = service.getById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping("/edit")
    public ModelAndView postEditNote(@RequestParam("id") long id,
                                     @RequestParam("title") String title,
                                     @RequestParam("content") String content) {
        Note note = service.buildNote(id, title, content);
        service.update(note);
        return new ModelAndView("redirect:list");
    }

    @GetMapping("/add")
    public ModelAndView getAddNote() {
        return new ModelAndView("note/add");
    }

    @PostMapping("/add")
    public ModelAndView postAddNote(@RequestParam("title") String title,
                                    @RequestParam("content") String content) {
        Note note = service.buildNote(title, content);
        service.add(note);
        return new ModelAndView("redirect:list");
    }
}
