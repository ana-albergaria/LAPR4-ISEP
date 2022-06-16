package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public abstract class Criteria implements ValueObject, Serializable {

    public enum Type {
        GENDER, AGE, PRODUCT_BOUGHT, PRODUCT_CATEGORY_BOUGHT;
    }

    @Enumerated(EnumType.STRING)
    private Type type;

    public Criteria(final Type type) {
        this.type = type;
    }

    public Criteria(){
        //for ORM purposes
    }

    public abstract boolean verifyCriteria(Client client);
}
