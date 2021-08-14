package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double value;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public Result() {
    }

    public static class ResultBuilder extends Builder<Result> {

        private ResultBuilder() {
            super(new Result());
        }

        public ResultBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public ResultBuilder withValue(double value) {
            add(e -> e.value = value);
            return this;
        }

        public ResultBuilder withAnimal(Animal animal) {
            add(e -> e.animal = animal);
            return this;
        }

        public ResultBuilder withSurvey(Survey survey) {
            add(e -> e.survey = survey);
            return this;
        }

    }

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }

    public long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", value=" + value +
                ", animal=" + animal +
                ", survey=" + survey +
                '}';
    }

}
