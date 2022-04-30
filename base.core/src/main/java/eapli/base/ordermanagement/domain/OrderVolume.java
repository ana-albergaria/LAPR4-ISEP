package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
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

    public static OrderVolume valueOf(final Long volume) {
        return new OrderVolume(volume);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof OrderVolume)) {
            return false;
        } else {
            OrderVolume that = (OrderVolume) o;
            return this.volume.equals(that.volume);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.volume).code();
    }

    public String toString() {
        return "Order Volume: " + this.volume;
    }
}
