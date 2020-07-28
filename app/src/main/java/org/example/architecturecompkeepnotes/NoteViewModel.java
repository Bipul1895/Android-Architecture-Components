package org.example.architecturecompkeepnotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;


public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private List<Note> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public List<Note> getAllNotes() {
        return allNotes;
    }

}
