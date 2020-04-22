package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
    private String name;

    public Animal() {
    }

    public Animal(Poll poll, String name) {
        this.poll = poll;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", poll=" + poll +
                ", name='" + name + '\'' +
                '}';
    }
}
