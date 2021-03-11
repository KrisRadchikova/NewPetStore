package by.tms.petstore.repository;

import by.tms.petstore.model.User;
import by.tms.petstore.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserStatus(UserStatus userStatus);

    User findUserByUserName(String userName);
}
