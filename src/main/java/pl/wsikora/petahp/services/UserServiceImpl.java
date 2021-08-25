package pl.wsikora.petahp.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wsikora.petahp.model.entities.User;
import pl.wsikora.petahp.model.repositories.UserRepository;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public User getCurrentlyLoggedIn(Principal principal) {
        return repository.findUserByEmail(principal.getName());
    }

    @Override
    public User createAndSave(String name, String email, String password) {
        return repository.save(User.builder()
                .withName(name)
                .withEmail(email)
                .withPassword(encoder.encode(password))
                .build());
    }

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean notExists(String email) {
        return !exists(email);
    }

}
