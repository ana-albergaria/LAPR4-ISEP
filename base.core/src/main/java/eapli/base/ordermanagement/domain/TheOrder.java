package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class TheOrder implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status {
        REGISTERED, PAYMENT_PENDING, TO_BE_PREPARED, BEING_PREPARED_ON_WAREHOUSE,
        READY_FOR_PACKAGING, READY_FOR_CARRIER_DISPATCHING, DISPATCHED, DELIVERED_BY_CARRIER, RECEIVED_BY_COSTUMER;
    }

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private Client client;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    //TYPED/SELECT BILLING AND DELIVERING ADDRESS?

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "billing_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "billing_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "billing_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country"))
    })
    private Address billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "shipping_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "shipping_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country"))
    })
    private Address shippingAddress;

    // COMENTAR PORQUE O PRODUCT AINDA NÃO É PERSISTIDO
    /*@OneToMany
    private Set<Product> products = new HashSet<>();*/

    /**
     * Map where:
     * > Key: a String containing the Product Unique Internal Code
     * > Value: is its quantity
     */
    @ElementCollection
    @CollectionTable(name = "quantities_per_product",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_reference")
    @Column(name = "quantity")
    private Map<String, Integer> quantitiesPerProduct = new HashMap<>();

    /**
     * Map where:
     * > Key: a String containing the Product Unique Internal Code
     * > Value: is its unitary price
     */
    @ElementCollection
    @CollectionTable(name = "unitary_prices_per_product",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_reference")
    @Column(name = "unitary_price")
    private Map<String, Double> unitaryPricesPerProduct = new HashMap<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "no_taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "no_taxes_currency"))
    })
    private Money totalAmountWithoutTaxes;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "taxes_currency"))
    })
    private Money totalAmountWithTaxes;

    //CONFIRMAR SHIPMENT
    @Enumerated(EnumType.STRING)
    private Shipment shipment;

    //FALTA COLOCAR PAYMENT - DÚVIDA

    /*
    DÚVIDA ENUNCIADO - ...the shipment cost computation, and the connections to external systems (e.g.: carriers and payment services) should be mock.
     */

    //falta implementar value object c metodo pra calcular
    private OrderVolume orderVolume;

    //falta implementar value object c metodo pra calcular
    private OrderWeight orderWeight;

    /*FALTA COLOCAR ATRIBUTOS ADICIONAIS PARA QUANDO É O SALES CLERK A REGISTAR ORDER
    Despite identifying the clerk registering the order, it is important to register (i) the source channel (e.g.: phone, email, meeting, etc...), (ii) the date/time when such interaction happen and (iii) optionally add some comment.
     */
    

    protected TheOrder() {
        //for ORM purposes
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
        return this.orderId;
    }




}
