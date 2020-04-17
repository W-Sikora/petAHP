package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select u.password from users u where u.email = 1?")
    String getUsersPasswordByEmail(String email);
}
