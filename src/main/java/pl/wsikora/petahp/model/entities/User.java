package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;
import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Deprecated
    public User() {
    }

    public static class UserBuilder extends Builder<User> {

        protected UserBuilder() {
            super(new User());
        }

        public UserBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public UserBuilder withName(String name) {
            add(e -> e.name = name);
            return this;
        }

        public UserBuilder withEmail(String email) {
            add(e -> e.email = email);
            return this;
        }

        public UserBuilder withPassword(String password) {
            add(e -> e.password = password);
            return this;
        }

        public UserBuilder withCreationDate(LocalDateTime creationDate) {
            add(e -> e.creationDate = creationDate);
            return this;
        }

    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

}
