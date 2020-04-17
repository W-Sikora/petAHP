package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "criteria_result")
public class CriterionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;
    @OneToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;
    private Double value;

    public CriterionResult() {
    }

    public CriterionResult(Criterion criterion, Evaluator evaluator, Double value) {
        this.criterion = criterion;
        this.evaluator = evaluator;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CriterionResult{" +
                "id=" + id +
                ", criterion=" + criterion +
                ", evaluator=" + evaluator +
                ", value=" + value +
                '}';
    }
}
