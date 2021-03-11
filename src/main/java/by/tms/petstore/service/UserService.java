package by.tms.petstore.service;

import by.tms.petstore.model.User;
import by.tms.petstore.repository.UserRepository;
import by.tms.petstore.resource.exception.ExistsException;
import by.tms.petstore.resource.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class UserService {

    private UserRepository userRepository;
    private KeyService keyService;


    //get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by name
    public User findUserByUserName(String userName) {
        if (userRepository.findUserByUserName(userName) != null) {
            return userRepository.findUserByUserName(userName);
        }
        throw new NotFoundException("Some wrong, user with name " + userName + " not found");
    }

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

    public String auth(User user) {
        User thisParticularUser = findUserByUserName(user.getUserName());
        if (thisParticularUser != null) {
            if (thisParticularUser.equals(user)) {
                UUID key = UUID.randomUUID();
                String uuidKey = key.toString();
                keyService.saveKey(uuidKey, thisParticularUser);
                return uuidKey;
            } else {
                return null;
            }
        } else
            throw new NotFoundException("This user not found");
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository, KeyService keyService) {
        this.userRepository = userRepository;
        this.keyService = keyService;
    }

}
