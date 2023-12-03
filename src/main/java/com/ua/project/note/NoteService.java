package com.ua.project.note;

import org.springframework.stereotype.Service;
import com.ua.project.crud.CRUDService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class NoteService implements CRUDService<Note> {
    private final List<Note> notes = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(notes);
    }

    @Override
    public Note add(Note element) {
        checkNoteArgs(element.getTitle(), element.getContent());
        element.setId(createRandomId());
        notes.add(element);
        return element;
    }

    @Override
    public void deleteById(long id) {
        notes.remove(getById(id));
    }

    @Override
    public void update(Note element) {
        checkNoteArgs(element.getTitle(), element.getContent());
        Note note = getById(element.getId());
        note.setTitle(element.getTitle());
        note.setContent(element.getContent());
    }

    @Override
    public Note getById(long id) {
        for (Note note: notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        throw new NullPointerException("Note does not exist");
    }

    private void checkNoteArgs(String title, String content) {
        Objects.requireNonNull(title, "Note title can not be a null");
        Objects.requireNonNull(content, "Note content can not be a null");

        if (title.trim().isBlank() || content.trim().isBlank()) {
            throw new IllegalArgumentException("Note can not have empty fields");
        }
    }

    private long createRandomId() {
        // there is a chance to reach end of all possible random values
        long randId = random.nextLong(0, Long.MAX_VALUE);

        try {
            getById(randId);
            return createRandomId();
        } catch (NullPointerException e) {
            return randId;
        }
    }

    public Note buildNote(String title, String content) {
        return buildNote(createRandomId(), title, content);
    }

    public Note buildNote(long id, String title, String content) {
        checkNoteArgs(title, content);
        return Note.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
