package org.example.architecturecompkeepnotes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //If we want to perform an operation which is not default, mention it explicitly
    //Note that we are using Query annotation for delete here
    //Benefit : we get compile-time error if we perform a typo
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //Note that the return type is List<Note>, eliminating the need of explicitly
    //using a Cursor object
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    List<Note> getAllNotes();

}
