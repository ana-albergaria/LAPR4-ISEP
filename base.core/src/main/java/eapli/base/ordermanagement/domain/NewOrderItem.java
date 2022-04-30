package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NewOrderItem implements ValueObject, Serializable {
    private final String code;

    private final Integer quantity;

    public NewOrderItem(final String code, final Integer quantity) {
        Preconditions.nonEmpty(code, "Code should neither be null nor empty");
        Preconditions.nonNull(quantity, "Quantity should not be null");
        Preconditions.isPositive(quantity, "The quantity of the wished Product must be positive.");
        this.code = code;
        this.quantity = quantity;
    }

    protected NewOrderItem() {
        this.code = "";
        this.quantity = null;
    }

    public static NewOrderItem valueOf(final String code, final Integer quantity) {
        return new NewOrderItem(code, quantity);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof NewOrderItem)) {
            return false;
        } else {
            NewOrderItem that = (NewOrderItem) o;
            return this.code.equals(that.code) && this.quantity.equals(that.quantity);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.code).with(this.quantity).code();
    }

    @Override
    public String toString() {
        return "Produce Code: " + this.code + "| Quantity: " + this.quantity;
    }
}
