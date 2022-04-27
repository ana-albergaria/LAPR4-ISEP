package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class OrderStatus implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status {
        REGISTERED, PAYMENT_PENDING, TO_BE_PREPARED, BEING_PREPARED_ON_WAREHOUSE,
        READY_FOR_PACKAGING, READY_FOR_CARRIER_DISPATCHING, DISPATCHED, DELIVERED_BY_CARRIER, RECEIVED_BY_COSTUMER;
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public OrderStatus(final Status status) {
        this.status = status;
    }

    public OrderStatus() {
        //for ORM purposes
    }
}
