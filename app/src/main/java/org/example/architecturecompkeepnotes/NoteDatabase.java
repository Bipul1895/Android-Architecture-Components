package org.example.architecturecompkeepnotes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //Thread safety using synchronized
    public static synchronized NoteDatabase getInstance(Context context) {
        if(instance != null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,
                    "note_database")
                    .fallbackToDestructiveMigration()//change of version => recreate
                    .addCallback(roomCallBack)//for pre-population of db
                    .build();
        }

        return instance;
    }

    //For pre-population of db with sample values
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PrePopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PrePopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;
        //We do not have object of noteDao as field in the outer class
        // so we will get it from outer class' object
        private PrePopulateDbAsyncTask(NoteDatabase database) {
            noteDao = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1" , "Description 1", 1));
            noteDao.insert(new Note("Title 2" , "Description 2", 2));
            noteDao.insert(new Note("Title 3" , "Description 3", 3));

            return null;
        }

    }

}
