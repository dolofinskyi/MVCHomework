package com.ua.project.features.note;

import com.ua.project.features.crud.CRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService implements CRUDService<Note> {
    private final NoteRepository noteRepository;

    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note add(Note element) {
        return noteRepository.save(element);
    }

    @Override
    public void deleteById(long id) {
       noteRepository.deleteById(id);
    }

    @Override
    public void update(Note element) {
        noteRepository.save(element);
    }

    @Override
    public Note getById(long id) {
        return noteRepository
                .findById(id)
                .orElseThrow(NullPointerException::new);
    }
}
