package com.himanshurajput5151.journalApplication.repository;

import com.himanshurajput5151.journalApplication.entity.JournalBody;
import com.himanshurajput5151.journalApplication.entity.UserBody;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserBody, String> {


    UserBody findByUsername(String userName);
}
