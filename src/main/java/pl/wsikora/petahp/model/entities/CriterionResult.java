package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "criteria_result")
public class CriterionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;
    private Integer value;
    private Double weight;

    public CriterionResult() {
    }

    public CriterionResult(Poll poll, Animal animal, Criterion criterion, Integer value, Double weight) {
        this.poll = poll;
        this.animal = animal;
        this.criterion = criterion;
        this.value = value;
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CriterionResult{" +
                "id=" + id +
                ", poll=" + poll +
                ", animal=" + animal +
                ", criterion=" + criterion +
                ", value=" + value +
                ", weight=" + weight +
                '}';
    }
}
