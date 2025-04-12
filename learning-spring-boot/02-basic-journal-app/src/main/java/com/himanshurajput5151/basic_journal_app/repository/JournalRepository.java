package com.himanshurajput5151.basic_journal_app.repository;

import com.himanshurajput5151.basic_journal_app.entity.JournalRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalRecord, ObjectId> {
}
