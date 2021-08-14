package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public Animal() {
    }

    public static class AnimalBuilder extends Builder<Animal> {

        private AnimalBuilder() {
            super(new Animal());
        }

        public AnimalBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public AnimalBuilder withName(String name) {
            add(e -> e.name = name);
            return this;
        }

        public AnimalBuilder withSurvey(Survey survey) {
            add(e -> e.survey = survey);
            return this;
        }

    }

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", survey=" + survey +
                '}';
    }

}
