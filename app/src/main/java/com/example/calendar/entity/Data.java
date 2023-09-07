package com.example.calendar.entity;

import androidx.annotation.NonNull;

public enum Data {
    URL ("http://twinklingfox.pythonanywhere.com/quotes");

    final String title;

    Data(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
