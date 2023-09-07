package com.example.calendar.entity;

import com.example.calendar.App;

import java.util.List;

public class DbRequest implements IRequestService.DbMethods{

    public IRequestService.DbMethods getDbDao() {
        AppDatabase db = App.getInstance().getDatabase();
        return db.noteDao();
    }

    @Override
    public List<Note> getAll() {
        return getDbDao().getAll();
    }

    /*@Override
    public Note getById(int id) {
        return App.getInstance().getDatabase().noteDao().getById(id);
    }*/

    @Override
    public void insert(Note note) {
        getDbDao().insert(note);
    }

    @Override
    public void update(Note note) {
        getDbDao().update(note);
    }

    @Override
    public void delete(Note note) {
        getDbDao().delete(note);
    }
}
