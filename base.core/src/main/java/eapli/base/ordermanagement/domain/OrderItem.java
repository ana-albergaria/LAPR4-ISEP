package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrderItem implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    /*@Version
    private Long version;*/

    /*@Id
    @GeneratedValue
    private Long orderItemId;*/

    private int quantity;

    private Code item;

    public OrderItem(final int qty, final Code item) {
        Preconditions.isPositive(qty);
        Preconditions.nonNull(item);

        quantity = qty;
        this.item = item;
    }

    protected OrderItem() {
        //for ORM purposes
    }

    public Code product() {
        return item;
    }

    public int quantity() {
        return quantity;
    }


    /*@Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.orderItemId;
    }*/

}
