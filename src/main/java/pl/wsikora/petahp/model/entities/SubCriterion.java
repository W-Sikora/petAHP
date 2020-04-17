package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "sub_criteria")
public class SubCriterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
    @ManyToOne
    @JoinColumn(name = "parent_criterion_id")
    private Criterion criterion;
    private String name;
    private Double weight;

    public SubCriterion() {
    }

    public SubCriterion(Poll poll, Criterion criterion, String name, Double weight) {
        this.poll = poll;
        this.criterion = criterion;
        this.name = name;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
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
        return "SubCriterion{" +
                "id=" + id +
                ", poll=" + poll +
                ", criterion=" + criterion +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
