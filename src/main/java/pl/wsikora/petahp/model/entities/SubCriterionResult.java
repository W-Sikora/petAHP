package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "sub_criteria_result")
public class SubCriterionResult {
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
    @JoinColumn(name = "sub_criterion_id")
    private SubCriterion subCriterion;
    private Integer value;
    private Double weight;

    public SubCriterionResult() {
    }

    public SubCriterionResult(Poll poll, Animal animal, SubCriterion subCriterion, Integer value, Double weight) {
        this.poll = poll;
        this.animal = animal;
        this.subCriterion = subCriterion;
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

    public SubCriterion getSubCriterion() {
        return subCriterion;
    }

    public void setSubCriterion(SubCriterion subCriterion) {
        this.subCriterion = subCriterion;
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
        return "SubCriterionResult{" +
                "id=" + id +
                ", poll=" + poll +
                ", animal=" + animal +
                ", subCriterion=" + subCriterion +
                ", value=" + value +
                ", weight=" + weight +
                '}';
    }
}
