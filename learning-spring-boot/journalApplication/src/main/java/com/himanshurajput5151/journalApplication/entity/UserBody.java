package com.himanshurajput5151.journalApplication.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "UserCollection")
@NoArgsConstructor
public class UserBody {
    @Id
    private ObjectId userId;
    @Indexed(unique = true)
    @NonNull
    private String username;    //to store the username
    @NonNull
    private String password;    //to store the password
    @DBRef
    List<JournalBody> journalEntryIdRecord = new ArrayList<>();       //to store the id's of journalRecord for each user

}
