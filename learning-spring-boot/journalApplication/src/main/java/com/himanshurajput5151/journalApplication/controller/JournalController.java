package com.himanshurajput5151.journalApplication.controller;


import com.himanshurajput5151.journalApplication.entity.JournalBody;
import com.himanshurajput5151.journalApplication.entity.UserBody;
import com.himanshurajput5151.journalApplication.services.JournalService;
import com.himanshurajput5151.journalApplication.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntries(@PathVariable String username) {
        UserBody user = userService.findUserData(username);
        List<JournalBody> all = user.getJournalEntryIdRecord();
        if(all !=null && !all.isEmpty()) {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> journalBodyData(@RequestBody JournalBody journalBody, @PathVariable String username) {
        try {
            journalService.saveJournalBody(journalBody,username);
            return new ResponseEntity<>(journalBody, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}/{journalId}")
    public ResponseEntity<?> deleteRecord(@PathVariable String username, @PathVariable ObjectId journalId) {
        JournalBody body = journalService.findById(journalId).orElse(null);
        if(body != null) {
            journalService.deleteJournalBodyAndLink(username,journalId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{username}/{journalId}")
    public ResponseEntity<String> updateTheJournalBody(@RequestBody JournalBody journalBody,
                                                       @PathVariable String username,
                                                       @PathVariable ObjectId journalId) {
        boolean updated = journalService.saveJournalBody(journalBody, username, journalId);
        if (updated) {
            return ResponseEntity.ok("Journal updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal not found");
        }
    }
}
