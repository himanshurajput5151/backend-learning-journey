package com.himanshurajput5151.journalApplication.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Document(collection = "JournalCollection")
@NoArgsConstructor
public class JournalBody {
    @Id
    private ObjectId journalID;
    @NonNull
    private String name;
    @NonNull
    private String title;
    private LocalDate date;


}
