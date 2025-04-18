package com.himanshurajput5151.journalApplication.repository;

import com.himanshurajput5151.journalApplication.entity.JournalBody;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalBody, ObjectId> {
}
