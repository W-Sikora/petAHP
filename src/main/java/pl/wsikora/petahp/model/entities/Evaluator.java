package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "evaluators")
public class Evaluator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
    private String name;
    private Double weight;

    public Evaluator() {
    }

    public Evaluator(Poll poll, String name, Double weight) {
        this.poll = poll;
        this.name = name;
        this.weight = weight;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Evaluator{" +
                "id=" + id +
                ", poll=" + poll +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
