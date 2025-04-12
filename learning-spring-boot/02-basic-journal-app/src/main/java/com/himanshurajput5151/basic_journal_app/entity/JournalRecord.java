package com.himanshurajput5151.basic_journal_app.entity;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Collections;

@Document()
public class JournalRecord {
    private ObjectId myID;
    private String name;
    private String title;
    private LocalDate date;

    public ObjectId getMyID() {
        return myID;
    }

    public void setMyID(ObjectId myID) {
        this.myID = myID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
