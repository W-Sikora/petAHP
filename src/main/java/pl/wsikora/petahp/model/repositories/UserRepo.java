package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from users where users.email = ?1")
    User findUserByEmail(String email);

    @Query(nativeQuery = true, value = "select password from users where users.email = ?1")
    String findPasswordByEmail(String email);

    @Query(nativeQuery = true, value = "select u.id from users u where u.email = ?1")
    long getIdByEmail(String email);
}
