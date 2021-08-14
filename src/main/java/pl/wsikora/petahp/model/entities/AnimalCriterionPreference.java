package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;

@Entity
@Table(name = "animals_criteria_preferences")
public class AnimalCriterionPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;

    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Deprecated
    public AnimalCriterionPreference() {
    }

    public static class AnimalCriterionPreferenceBuilder extends Builder<AnimalCriterionPreference> {

        private AnimalCriterionPreferenceBuilder() {
            super(new AnimalCriterionPreference());
        }

        public AnimalCriterionPreferenceBuilder withId(long id){
            add(e -> e.id = id);
            return this;
        }

        public AnimalCriterionPreferenceBuilder withWeight(double weight){
            add(e -> e.weight = weight);
            return this;
        }

        public AnimalCriterionPreferenceBuilder withSurvey(Survey survey){
            add(e -> e.survey = survey);
            return this;
        }

        public AnimalCriterionPreferenceBuilder withEvaluator(Evaluator evaluator){
            add(e -> e.evaluator = evaluator);
            return this;
        }

        public AnimalCriterionPreferenceBuilder withCriterion(Criterion criterion){
            add(e -> e.criterion = criterion);
            return this;
        }

        public AnimalCriterionPreferenceBuilder withAnimal(Animal animal){
            add(e -> e.animal = animal);
            return this;
        }

    }

    public static AnimalCriterionPreferenceBuilder builder() {
        return new AnimalCriterionPreferenceBuilder();
    }

    public long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Survey getSurvey() {
        return survey;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public String toString() {
        return "AnimalCriterionPreference{" +
                "id=" + id +
                ", weight=" + weight +
                ", survey=" + survey +
                ", evaluator=" + evaluator +
                ", criterion=" + criterion +
                ", animal=" + animal +
                '}';
    }

}
