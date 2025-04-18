package com.himanshurajput5151.journalApplication.services;


import com.himanshurajput5151.journalApplication.entity.UserBody;
import com.himanshurajput5151.journalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //returning single userData
    public UserBody findUserData(String userName) {
        UserBody user = userRepository.findByUsername(userName);
        if(user!=null) {
            return user;
        }
        return null;

    }


    //returning all user data from service to controller
    public List<UserBody> usersData(){
        return userRepository.findAll();
    }


    //saving the users username and password into Database
    public UserBody saveDataInDatabase(UserBody user) {
        return userRepository.save(user);
    }

    //deleting the user from database by using there usernames
    public void deleteTheUserData(UserBody user){
        userRepository.deleteById(user.getUserId().toString());

    }

    //updating the users username and passwords
    public UserBody updateUser(UserBody newData, String userName){
        UserBody oldData = userRepository.findByUsername(userName);
        if(oldData == null) {
            throw new RuntimeException("User Not Found with username" + userName);
        }

        boolean isUpdated = false;
        if (StringUtils.hasText(newData.getUsername())) {
            oldData.setUsername(newData.getUsername());
            isUpdated = true;
        }

        if (StringUtils.hasText(newData.getPassword())) {
            oldData.setPassword(newData.getPassword());
            isUpdated = true;
        }

        if (isUpdated) {
            return userRepository.save(oldData);
        } else {
            return oldData;
        }

    }

}

