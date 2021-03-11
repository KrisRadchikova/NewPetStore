package by.tms.petstore.repository;

import by.tms.petstore.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {
    Key findKeyByKeyName(String name);
}
