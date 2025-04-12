package com.himanshurajput5151.basic_journal_app.service;

import com.himanshurajput5151.basic_journal_app.entity.JournalRecord;
import com.himanshurajput5151.basic_journal_app.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    @Autowired
    private JournalRepository journalRepository ;

    public List<JournalRecord> showEntry(){
        return journalRepository.findAll();
    }

    public void saveEntry(JournalRecord record){
        record.setDate(LocalDate.now());
        journalRepository.save(record);
    }

    public Optional<JournalRecord> saveById(ObjectId myID){
        return journalRepository.findById(myID);
    }

    public void deleteAllEntry(JournalRecord journalRecord) {
        journalRepository.deleteAll();
    }

    public void deleteOneEntry(ObjectId objectId){
        journalRepository.deleteById(objectId);
    }

    public JournalRecord updateRecords(ObjectId myId, JournalRecord newEntry) {
        JournalRecord old = journalRepository.findById(myId).orElse(null);
        if(old!=null) {
            old.setName(newEntry.getName()!=null && !newEntry.getName().equals("") ? newEntry.getName() : old.getName());
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
        }
        journalRepository.save(old);
        return old;
    }
}
