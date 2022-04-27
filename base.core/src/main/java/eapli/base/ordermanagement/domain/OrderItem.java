package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long orderItemId;

    private int quantity;
    /*@ManyToOne
    private Product item;

    public OrderItem(final int qty, final Product item) {
        Preconditions.isPositive(qty);
        Preconditions.nonNull(item);

        quantity = qty;
        this.item = item;
    }*/

    protected OrderItem() {
        //for ORM purposes
    }

    /*public Product product() {
        return item;
    }

    public int quantity() {
        return quantity;
    }*/

    @Override
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
    }

}
