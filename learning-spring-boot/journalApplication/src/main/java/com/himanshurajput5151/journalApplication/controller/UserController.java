
package com.himanshurajput5151.journalApplication.controller;



        import com.himanshurajput5151.journalApplication.entity.UserBody;
        import com.himanshurajput5151.journalApplication.services.UserService;
        import org.apache.catalina.User;
        import org.bson.types.ObjectId;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ProblemDetail;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //to get data of single User
    @GetMapping("/{userName}")
    public ResponseEntity<?> getSingleUserDetails(@PathVariable String userName) {
        UserBody user = userService.findUserData(userName);
        return (user != null)
                ? ResponseEntity.ok(user) // 200 OK with user
                : ResponseEntity.notFound().build(); // 404 Not Found

    }


    //to get data of all User
    @GetMapping()
    public ResponseEntity<List<UserBody>> getAllUsersData() {
        List<UserBody> allUserData = userService.usersData();
        return new ResponseEntity<>(allUserData, HttpStatus.OK);
    }


    //to save the users data to database coming form client request
    @PostMapping()
    public ResponseEntity<?> saveUserData(@RequestBody UserBody userData) {
        UserBody user = userService.saveDataInDatabase(userData);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //delete the user from database using there username
    @DeleteMapping({"/{username}"})
    public ResponseEntity<?> deleteUserData(@PathVariable String username) {
        UserBody user = userService.findUserData(username);
        if(user != null) {
            userService.deleteTheUserData(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //update the data of user
    @PutMapping("/{username}")
    public ResponseEntity<?> updateTheUserData(@RequestBody UserBody newData, @PathVariable String username) {
        try{
            UserBody updatedUser = userService.updateUser(newData, username);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}

