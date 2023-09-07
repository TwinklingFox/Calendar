package com.example.calendar.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.calendar.domain.ApiRequestResult;

import java.io.IOException;
import java.util.List;

public interface IRequestService {
    interface ApiMethods {
        ApiRequestResult getApiResult() throws IOException;
    }

    @Dao
    interface DbMethods {
        @Query("SELECT * FROM notes")
        List<Note> getAll();

        /*@Query("SELECT * FROM notes WHERE id = :id")
        Note getById(int id);*/

        @Insert
        void insert(Note note);

        @Update
        void update(Note note);

        @Delete
        void delete(Note note);
    }
}
