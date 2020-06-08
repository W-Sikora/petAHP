package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String email);

    @Query("select u.password from User u where u.email = ?1")
    String findPasswordByEmail(String email);

}
