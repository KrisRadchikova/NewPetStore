package by.tms.petstore.service;

import by.tms.petstore.model.User;
import by.tms.petstore.repository.UserRepository;
import by.tms.petstore.resource.exception.ExistsException;
import by.tms.petstore.resource.exception.InvalidSuppliedException;
import by.tms.petstore.resource.exception.NotFoundException;
import by.tms.petstore.storage.UserTokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserTokenStorage userTokenStorage;

    //get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by name

    //get user by id
    public Optional<User> findUserById(long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id);
        }
        throw new NotFoundException("User with id " + id + " not found");
    }

    //updated user
    public User updateUser(User userRequest) {
        userRepository.findById(userRequest.getId()).map(user -> {
            user.setUserName(userRequest.getUserName());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setPhone(userRequest.getPhone());
            return userRepository.save(user);
        });
        return userRequest;
    }

    // delete user
    public void deleteUserById(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        throw new NotFoundException("User with id " + id + " not found");
    }

    //add new user
    public User addNewUser(User user) {
        ExampleMatcher addNewUserMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("userName", ignoreCase())
                .withMatcher("firstName", ignoreCase())
                .withMatcher("lastName", ignoreCase())
                .withMatcher("email", ignoreCase())
                .withMatcher("password", ignoreCase())
                .withMatcher("phone", ignoreCase());
        Example<User> userExample = Example.of(user, addNewUserMatcher);
        if (!userRepository.exists(userExample)) {
            return userRepository.save(user);
        }
        throw new ExistsException("This user exists");
    }

    public boolean validationUser(String token) {
        return userTokenStorage.userTokenExists(token);
    }

    public void addUserToken(String token) {
        userTokenStorage.saveUserToken(token);
    }

    public String getUserToken(String tokens) {
        List<String> allUserTokens = userTokenStorage.getAllUserTokens();
        for (String token : allUserTokens) {
            if (token.equals(tokens)) {
                return token;
            }
        }
        throw new InvalidSuppliedException("Invalid token supplied");
    }

    @Autowired
    public void setUserTokenStorage(UserTokenStorage userTokenStorage) {
        this.userTokenStorage = userTokenStorage;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
