package eapli.base.warehousemanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Bin.
 *
 * @author Marta Ribeiro 1201592
 */
@Entity
public class Bin implements AggregateRoot<Long>, Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long binID;

    @Column(name = "volume_in_cubic_meters")
    private Double size;

    @OneToOne
    @JoinColumn(name = "aisle_id")
    private Aisle aisle;

    @OneToOne
    @JoinColumn(name = "row_id")
    private TheRow row;

    @OneToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Bin(final Double size, final Aisle aisle, final TheRow row, final Shelf shelf, final Product product){
        Preconditions.noneNull(size,aisle,row,shelf,product);
        this.size=size;
        this.aisle=aisle;
        this.row=row;
        this.shelf=shelf;
        this.product=product;
    }

    protected Bin(){}

    public Double size() {
        return size;
    }

    public Aisle aisle() {
        return aisle;
    }

    public TheRow row() {
        return row;
    }

    public Shelf shelf() {
        return shelf;
    }

    public Product product() {
        return product;
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Bin)) {
            return false;
        }

        final var that = (Bin) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return binID;
    }

}
