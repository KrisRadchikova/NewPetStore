package by.tms.petstore.resource;

import by.tms.petstore.model.User;
import by.tms.petstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserResource {

    private UserService userService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Get all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/getUser/{id}")
    public Optional<User> getUserById(@PathVariable long id) {
        log.info("Get user by Id " + id);
        return userService.findUserById(id);
    }

    @PostMapping(path = "/add")
    public User addNewUser(@RequestBody User user) {
        log.info("Create user " + user.getUserName());
        return userService.addNewUser(user);
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<String> authorization(@RequestBody User user) {
        if (userService.auth(user) != null) {
            return new ResponseEntity<>(userService.auth(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping(path = "/update")
    public User updateUser(@RequestBody User user) {
        log.info("Update user " + user.getUserName());
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        log.info("Delete user with ID " + id);
        userService.deleteUserById(id);
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
