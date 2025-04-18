package com.himanshurajput5151.journalApplication.services;

import com.himanshurajput5151.journalApplication.entity.JournalBody;
import com.himanshurajput5151.journalApplication.entity.UserBody;
import com.himanshurajput5151.journalApplication.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;



    public Optional<JournalBody> findById(ObjectId journalId) {
         return journalRepository.findById(journalId);
    }


    @Transactional
    public void saveJournalBody(JournalBody journalBody, String username) {
        UserBody user = userService.findUserData(username);
        journalBody.setDate(LocalDate.now());
        JournalBody body = journalRepository.save(journalBody);
        user.getJournalEntryIdRecord().add(body);
        userService.saveDataInDatabase(user);
    }




    public void deleteJournalBodyAndLink(String username, ObjectId journalId) {
        UserBody user = userService.findUserData(username);
        user.getJournalEntryIdRecord().removeIf(x-> x.getJournalID().equals(journalId));
        userService.saveDataInDatabase(user);
        journalRepository.findById(journalId);
    }


    public boolean saveJournalBody(JournalBody newBody, String username, ObjectId journalId) {
        JournalBody oldBody = journalRepository.findById(journalId).orElse(null);
        if(oldBody != null) {
            oldBody.setName(StringUtils.hasText(newBody.getName()) ? newBody.getName() : oldBody.getName());
            oldBody.setTitle(StringUtils.hasText(newBody.getTitle()) ? newBody.getTitle() : oldBody.getTitle());
            JournalBody body = journalRepository.save(oldBody);
            return true;
        }
        return false;
    }
}
