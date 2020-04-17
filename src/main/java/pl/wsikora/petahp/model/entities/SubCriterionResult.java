package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "sub_criteria_result")
public class SubCriterionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "sub_criterion_id")
    private SubCriterion subCriterion;
    @OneToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;
    private Double value;

    public SubCriterionResult() {
    }

    public SubCriterionResult(SubCriterion subCriterion, Evaluator evaluator, Double value) {
        this.subCriterion = subCriterion;
        this.evaluator = evaluator;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubCriterion getSubCriterion() {
        return subCriterion;
    }

    public void setSubCriterion(SubCriterion subCriterion) {
        this.subCriterion = subCriterion;
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
        return "SubCriterionResult{" +
                "id=" + id +
                ", subCriterion=" + subCriterion +
                ", evaluator=" + evaluator +
                ", value=" + value +
                '}';
    }
}
