package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class TheOrder implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private Client client;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    //FALTA COLOCAR TYPED/SELECT BILLING AND DELIVERING ADDRESS

    // COMENTAR PORQUE O PRODUCT AINDA NÃO É PERSISTIDO
    /*@OneToMany
    private Set<Product> products = new HashSet<>();*/

    /**
     * Map where:
     * > Key: a String containing the Product Reference
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
     * > Key: a String containing the Product Reference
     * > Value: is its unitary price
     */
    @ElementCollection
    @CollectionTable(name = "unitary_prices_per_product",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_reference")
    @Column(name = "unitary_price")
    private Map<String, Double> unitaryPricesPerProduct = new HashMap<>();


    // TEMOS DE SER NÓS A FAZER COPY PASTE DA CLASSE MONEY SENÃO DÁ ERROS A CORRER A BASE DE DADOS
    @ElementCollection
    List<Money> list = new ArrayList<>();

    //private eapli.base.ordermanagement.domain.Money totalAmountWithoutTaxes;

    //private Money totalAmountWithTaxes;

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

    //FALTA STATUS DA ORDER

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
