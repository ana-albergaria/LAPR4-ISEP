package eapli.base.shoppingcartmanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ShopCarItem implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long shopCarId;

    private int quantity;

    @ManyToOne
    private Product product;

    public ShopCarItem(final int quantity, final Product product) {
        this.product = product;
        this.quantity = quantity;
    }

    protected ShopCarItem() {
        //for ORM purposes
    }

    public int quantity() {
        return this.quantity;
    }

    public Product product() {
        return this.product;
    }

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
        return this.shopCarId;
    }

}
