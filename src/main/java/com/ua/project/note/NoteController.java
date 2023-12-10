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
    public ModelAndView deleteNote(long id) {
        service.deleteById(id);
        return new ModelAndView("redirect:list");
    }

    @GetMapping("/edit")
    public ModelAndView getEditNote(long id) {
        ModelAndView result = new ModelAndView("note/edit");
        Note note = service.getById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping("/edit")
    public ModelAndView postEditNote(long id, String title, String content) {
        Note note = service.getById(id);
        note.setTitle(title);
        note.setContent(content);
        service.update(note);
        return new ModelAndView("redirect:list");
    }

    @GetMapping("/add")
    public ModelAndView getAddNote() {
        return new ModelAndView("note/add");
    }

    @PostMapping("/add")
    public ModelAndView postAddNote(String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        service.add(note);
        return new ModelAndView("redirect:list");
    }
}
