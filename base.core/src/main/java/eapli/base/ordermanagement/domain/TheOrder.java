package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.productmanagement.application.ListProductService;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
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
    private OrderAddress billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "shipping_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "shipping_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country"))
    })
    private OrderAddress shippingAddress;

    @ElementCollection
    private Set<OrderItem> items;



    /**
     * Map where:
     * > Key: Unique Internal Code of Product
     * > Value: an Order Item entity
     *
     * Might be useful for future US's, namely related to the Shopping Cart
     */
    /*@OneToMany
    @JoinTable(name = "map_items",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    @MapKeyJoinColumn(name = "product_code")
    private Map<Code, OrderItem> mapItems = new HashMap<>();*/

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

    @Enumerated(EnumType.STRING)
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

    @ManyToOne
    private SystemUser salesClerk;

    public TheOrder(final Client client, final OrderAddress billingAddress, final OrderAddress shippingAddress, final Shipment shipment, final Payment payment, final SourceChannel sourceChannel, final Calendar interactionDate, final AdditionalComment additionalComment, final SystemUser salesClerk, final Set<OrderItem> items) {
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
        this.salesClerk = salesClerk;
        this.totalAmountWithoutTaxes = obtainTotalAmountWithoutTaxes(new ListProductService());
        this.totalAmountWithTaxes = obtainTotalAmountWithTaxes(new ListProductService());
        this.orderVolume = obtainTotalOrderVolume(new ListProductService());
        this.orderWeight = obtainTotalOrderWeight(new ListProductService());
        this.status = new OrderStatus(OrderStatus.Status.TO_BE_PREPARED);
    }

    public TheOrder(final Client client, final OrderAddress billingAddress, final OrderAddress shippingAddress, final Shipment shipment, final Payment payment, final SourceChannel sourceChannel, final Calendar interactionDate, final SystemUser salesClerk, final Set<OrderItem> items) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.shipment = shipment;
        this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
        this.salesClerk = salesClerk;
        this.totalAmountWithoutTaxes = obtainTotalAmountWithoutTaxes(new ListProductService());
        this.totalAmountWithTaxes = obtainTotalAmountWithTaxes(new ListProductService());
        this.orderVolume = obtainTotalOrderVolume(new ListProductService());
        this.orderWeight = obtainTotalOrderWeight(new ListProductService());
        this.status = new OrderStatus(OrderStatus.Status.TO_BE_PREPARED);
    }


    protected TheOrder() {
        //for ORM purposes
    }

    public Money obtainTotalAmountWithoutTaxes(ListProductService svcProducts) {
        double totalAmountWithoutTaxes = 0;

        for (OrderItem orderItem : items) {
            String code = orderItem.code();
            Optional<Product> product = svcProducts.findProductById(Code.valueOf(code));
            if(product.isPresent()) {
                totalAmountWithoutTaxes += (orderItem.quantity() * product.get().getPriceWithoutTaxes().amountAsDouble());
            }
        }
        return this.totalAmountWithoutTaxes = Money.euros(totalAmountWithoutTaxes);
    }

    public Money obtainTotalAmountWithTaxes(ListProductService svcProducts) {
        double totalAmountWithTaxes = 0;

        for (OrderItem orderItem : items) {
            String code = orderItem.code();
            Optional<Product> product = svcProducts.findProductById(Code.valueOf(code));
            if(product.isPresent()) {
                totalAmountWithTaxes += (orderItem.quantity() * product.get().getPriceWithTaxes().amountAsDouble());
            }
        }
        return this.totalAmountWithTaxes = Money.euros(totalAmountWithTaxes + this.shipment.cost());
    }

    public OrderWeight obtainTotalOrderWeight(ListProductService svcProducts) {
        double totalWeight = 0;

        for (OrderItem orderItem : items) {
            String code = orderItem.code();
            Optional<Product> product = svcProducts.findProductById(Code.valueOf(code));
            if(product.isPresent()) {
                totalWeight += (orderItem.quantity() * product.get().getWeight().weight());
            }
        }
        return this.orderWeight = new OrderWeight(totalWeight);
    }

    public OrderVolume obtainTotalOrderVolume(ListProductService svcProducts) {
        double totalVolume = 0;

        for (OrderItem orderItem : items) {
            String code = orderItem.code();
            Optional<Product> product = svcProducts.findProductById(Code.valueOf(code));
            if(product.isPresent()) {
                totalVolume += (orderItem.quantity() * product.get().getVolume().volume());
            }

        }
        return this.orderVolume = new OrderVolume(totalVolume);
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
