package by.tms.petstore.resource;

import by.tms.petstore.model.User;
import by.tms.petstore.service.UserService;
import by.tms.petstore.storage.UserTokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    private UserService userService;

    private UserTokenStorage userTokenStorage;

   /* public static void main(String[] args) {
        UUID uuidForUser = UUID.randomUUID();
        System.out.println(uuidForUser.toString());
    }*/

    @PostMapping(path = "/authorization")
    public ResponseEntity<String> auth(@RequestBody User user) {
        if (userService.getAllUsers().contains(user)) {
            UUID uuidForUser = UUID.randomUUID();
            String s = uuidForUser.toString();
            userTokenStorage.saveUserToken(s);
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/getUser/{id}")
    public Optional<User> getUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PostMapping(path = "/add")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @PutMapping(path = "/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }


    @Autowired
    public void setUserTokenStorage(UserTokenStorage userTokenStorage) {
        this.userTokenStorage = userTokenStorage;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
