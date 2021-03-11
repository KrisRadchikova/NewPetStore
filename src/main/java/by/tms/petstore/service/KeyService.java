package by.tms.petstore.service;

import by.tms.petstore.model.Key;
import by.tms.petstore.model.User;
import by.tms.petstore.model.UserStatus;
import by.tms.petstore.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyService {

    private KeyRepository keyRepository;

    @Autowired
    public void setKeyRepository(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    //получение всех ключей
    public List<Key> getAllKeys() {
        return keyRepository.findAll();
    }

    public UserStatus validKey(String key) {
        Key key1 = keyRepository.findKeyByKeyName(key);
        if (key1 != null) {
            if (key1.getUser().getUserStatus().equals(UserStatus.ADMIN)) {
                return UserStatus.ADMIN;
            } else
                return UserStatus.USER;
        } else
            return UserStatus.QUEST;
    }

    public void saveKey(String name, User user) {
        keyRepository.save(new Key(name, user));
    }

}
