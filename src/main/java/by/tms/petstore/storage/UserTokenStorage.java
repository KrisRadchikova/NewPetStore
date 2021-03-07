package by.tms.petstore.storage;

import by.tms.petstore.repository.UserTokenRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UserTokenStorage {

    private UserTokenRepository userTokenRepository;

    private List<String> stringList = new ArrayList<>();

    public void saveUserToken(String token) {
        stringList.add(token);
    }

    public boolean userTokenExists(String token) {
        return stringList.contains(token);
    }

    public List<String> getAllUserTokens() {
        return new ArrayList<>(stringList);
    }

    @Autowired
    public void setUserTokenRepository(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }
}
