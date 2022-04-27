package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderVolume implements ValueObject, Serializable {
    private final Long volume;

    public OrderVolume(final Long volume) {
        Preconditions.nonNull(volume, "The volume shouldn't be null.");
        Preconditions.isPositive(volume, "The volume should be positive.");
        this.volume = volume;
    }

    protected OrderVolume() {
        //for ORM purposes
        this.volume = null;
    }
}
