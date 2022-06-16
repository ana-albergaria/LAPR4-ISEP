package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Embeddable
public class TheRule implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    @ElementCollection
    private List<Criteria> criteria;

    public TheRule(final List<Criteria> criteria){
        Preconditions.noneNull(criteria,"Rule needs to have criteria!");
        this.criteria=criteria;
    }

    public TheRule(){
        //for ORM purposes
    }

    public List<Criteria> criteria(){
        return this.criteria;
    }

    public boolean equals(final Object o){
        return this==o;
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.criteria).code();
    }


}
