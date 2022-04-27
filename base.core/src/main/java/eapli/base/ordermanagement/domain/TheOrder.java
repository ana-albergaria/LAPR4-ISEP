package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.productmanagement.domain.Code;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.time.util.Calendars;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class TheOrder implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    public enum SourceChannel {
        CALL, EMAIL, MEETING;
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

    private OrderStatus status;

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

    @OneToMany
    Set<OrderItem> items = new HashSet<>();


    /**
     * Map where:
     * > Key: Unique Internal Code of Product
     * > Value: an Order Item entity
     *
     * Might be useful for future US's, namely related to the Shopping Cart
     */
    @OneToMany
    @JoinTable(name = "map_items",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    @MapKeyJoinColumn(name = "product_code")
    private Map<Code, OrderItem> mapItems = new HashMap<>();

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

    @Enumerated(EnumType.STRING)
    private Shipment shipment;

    private Payment payment;

    /*
    DÚVIDA ENUNCIADO - ...the shipment cost computation, and the connections to external systems (e.g.: carriers and payment services) should be mock.
     */

    //falta implementar value object c metodo pra calcular
    private OrderVolume orderVolume;

    //falta implementar value object c metodo pra calcular
    private OrderWeight orderWeight;

    //falta identificar SalesClerk

    @Enumerated(EnumType.STRING)
    private SourceChannel sourceChannel;

    @Temporal(TemporalType.DATE)
    private Calendar interactionDate;

    private AdditionalComment additionalComment;

    /*FALTA COLOCAR ATRIBUTOS ADICIONAIS PARA QUANDO É O SALES CLERK A REGISTAR ORDER
    Despite identifying the clerk registering the order, it is important to register (i) the source channel (e.g.: phone, email, meeting, etc...), (ii) the date/time when such interaction happen and (iii) optionally add some comment.
     */


    //NOS CONSTRUTORES FALTA: STATUS, SYSTEM USER, CALCULAR OS AMOUNTS COM TAXAS E SEM TAXAS, O VOLUME E O WEIGHT

    public TheOrder(final Client client, final Address billingAddress, final Address shippingAddress, final Set<OrderItem> items, final Shipment shipment, final Payment payment, final SourceChannel sourceChannel, final Calendar interactionDate, final AdditionalComment additionalComment) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.shipment = shipment;
        this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
        this.additionalComment = additionalComment;
    }

    public TheOrder(final Client client, final Address billingAddress, final Address shippingAddress, final Set<OrderItem> items, final Shipment shipment, final Payment payment, final SourceChannel sourceChannel, final Calendar interactionDate) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.shipment = shipment;
        this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
    }

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
