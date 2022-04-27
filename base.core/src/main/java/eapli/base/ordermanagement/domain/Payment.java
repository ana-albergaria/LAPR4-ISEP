package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class Payment implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Integer MAX_LENGTH = 512;

    public enum Type {
        PAYPAL, APPLE_PAY;
    }

    @Enumerated(EnumType.STRING)
    private final Type paymentType;

    private final String payment;

    public Payment(final Type type, final String payment) {
        Preconditions.nonEmpty(payment, "Payment payment should neither be null nor empty");
        Preconditions.ensure(payment.length() <= MAX_LENGTH, "Payment payment should not be longer than 512 chars");
        this.paymentType = type;
        this.payment = payment;
    }

    protected Payment() {
        this.paymentType = null;
        this.payment = null;
        //for ORM purposes
    }
}
