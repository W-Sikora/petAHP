package pl.wsikora.petahp.services;

import pl.wsikora.petahp.model.entities.User;

import java.security.Principal;

public interface UserService {

    User getCurrentlyLoggedIn(Principal principal);

    User createAndSave(String name, String email, String password);

    boolean exists(String email);

    boolean notExists(String email);

}
