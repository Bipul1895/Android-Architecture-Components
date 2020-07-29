package org.example.architecturecompkeepnotes;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * "Entity" annotation will create all the necessary code required to create a Note entity in db at
 * "compile-time"
 * By default name of the table will be Note (same as the class), but we can change it
 * Constructors and Getter methods are essential because this is the way that Room
 * sends this data to database.
 * Note that we do not have any parameter in constructor for id, because it will be
 * automatically generated. However, the way we let android know is by creating
 * a setter method for id.
 * //@ColumnInfo(name = "priority_column") to change name of a field in the table
 */


@Entity(tableName = "note_table")
public class Note {
    private static final String TAG = "Note";
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    //change name of this field in table
    //@ColumnInfo(name = "priority_column")
    private int priority;
    public Note(String title, String description, int priority) {
        Log.d(TAG, "Note: constructor starts");
        this.title = title;
        this.description = description;
        this.priority = priority;
        Log.d(TAG, "Note: constructor ends");
    }

    //Very important
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getPriority() {
        return priority;
    }
}