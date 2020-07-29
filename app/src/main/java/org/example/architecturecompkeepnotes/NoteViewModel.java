package org.example.architecturecompkeepnotes;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class NoteViewModel extends AndroidViewModel {
    private static final String TAG = "NoteViewModel";

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "NoteViewModel: constructor starts");
        this.repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
        Log.d(TAG, "NoteViewModel: constructor ends");
    }

    //wrapper methods
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

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

}
