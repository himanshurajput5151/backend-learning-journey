package com.himanshurajput5151.basic_journal_app.controller;

import com.himanshurajput5151.basic_journal_app.entity.JournalRecord;
import com.himanshurajput5151.basic_journal_app.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public List<JournalRecord> show(){
        return journalService.showEntry();
    }

    @GetMapping("id/{myID}")
    public JournalRecord savebyId(@PathVariable ObjectId myID){
        return journalService.saveById(myID).orElse(null);
    }

    @PostMapping
    public void save(@RequestBody JournalRecord journalRecord){
        journalService.saveEntry(journalRecord);
    }

    @DeleteMapping
    public boolean delete(JournalRecord journalRecord) {
        journalService.deleteAllEntry(journalRecord);
        return true;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId){
        journalService.deleteOneEntry(myId);
        return true;
    }

    @DeleteMapping()
    public boolean deleteAtOnce(JournalRecord journalRecord){
        journalService.deleteAllEntry(journalRecord);
        return true;
    }

    @PostMapping
    public JournalRecord updateById(@PathVariable ObjectId myID, @RequestBody JournalRecord journalRecord){
        return journalRecord;
    }
}
