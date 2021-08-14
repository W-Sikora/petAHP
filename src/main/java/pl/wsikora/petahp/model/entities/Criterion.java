package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;

@Entity
@Table(name = "criteria")
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "level_of_hierarchy")
    private int hierarchyLevel;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Criterion criterion;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public Criterion() {
    }

    public static class CriterionBuilder extends Builder<Criterion> {

        private CriterionBuilder() {
            super(new Criterion());
        }

        public CriterionBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public CriterionBuilder withName(String name) {
            add(e -> e.name = name);
            return this;
        }

        public CriterionBuilder withHierarchyLevel(int hierarchyLevel) {
            add(e -> e.hierarchyLevel = hierarchyLevel);
            return this;
        }

        public CriterionBuilder withCriterion(Criterion criterion) {
            add(e -> e.criterion = criterion);
            return this;
        }

        public CriterionBuilder withSurvey(Survey survey) {
            add(e -> e.survey = survey);
            return this;
        }

    }

    public static CriterionBuilder builder() {
        return new CriterionBuilder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getHierarchyLevel() {
        return hierarchyLevel;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String toString() {
        return "Criterion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hierarchyLevel=" + hierarchyLevel +
                ", criterion=" + criterion +
                ", survey=" + survey +
                '}';
    }

}
