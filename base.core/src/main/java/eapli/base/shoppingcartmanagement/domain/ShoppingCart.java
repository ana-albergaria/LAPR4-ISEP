package eapli.base.shoppingcartmanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ShoppingCart implements AggregateRoot<Long>, Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long shoppingCartId;

    @OneToOne
    private Client client;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public ShoppingCart(final Client client, final List<OrderItem> items) {
        this.client = client;
        this.items = items;
    }

    public ShoppingCart(final Client client) {
        this.client = client;
    }

    protected ShoppingCart() {
        //for ORM purposes
    }

    public Client client() {
        return this.client;
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
        return this.shoppingCartId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartId=" + shoppingCartId +
                ", client=" + client +
                ", items=" + items +
                '}';
    }
}
