package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "facts")
public class Fact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double measure;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public Fact() {
    }

    public Fact(Double measure, Animal animal, Criterion criterion, Survey survey) {
        this.measure = measure;
        this.animal = animal;
        this.criterion = criterion;
        this.survey = survey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMeasure() {
        return measure;
    }

    public void setMeasure(Double measure) {
        this.measure = measure;
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

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id=" + id +
                ", measure=" + measure +
                ", animal=" + animal +
                ", criterion=" + criterion +
                ", survey=" + survey +
                '}';
    }
}
