package eapli.base.surveymanagement.domain;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.Code;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Questionnaire implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String code;

    public Questionnaire(final String code){
        this.code=code;
    }

    protected Questionnaire(){

    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        throw new IllegalArgumentException("to develop");
    }

    @Override
    public String identity() {
        return code;
    }


}
