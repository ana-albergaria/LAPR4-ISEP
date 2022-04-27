package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderWeight implements ValueObject, Serializable {
    private final Long weight;

    public OrderWeight(final Long weight) {
        Preconditions.nonNull(weight, "The volume shouldn't be null.");
        Preconditions.isPositive(weight, "The volume should be positive.");
        this.weight = weight;
    }

    protected OrderWeight() {
        //for ORM purposes
        this.weight = null;
    }


}
