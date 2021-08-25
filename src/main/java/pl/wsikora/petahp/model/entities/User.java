package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;
import pl.wsikora.petahp.utils.BuilderTemplate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    private boolean enabled;

    @Deprecated
    public User() {
    }

    public static Builder builder() {
        return new Builder(new User());
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        enabled = true;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public static class Builder extends BuilderTemplate<User> {

        private Builder(User user) {
            super(user);
        }

        public Builder withName(String name) {
            add(e -> e.name = name);
            return this;
        }

        public Builder withEmail(String email) {
            add(e -> e.email = email);
            return this;
        }

        public Builder withPassword(String password) {
            add(e -> e.password = password);
            return this;
        }

    }

}
