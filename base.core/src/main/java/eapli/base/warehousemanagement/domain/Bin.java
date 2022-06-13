package eapli.base.warehousemanagement.domain;

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

    private Double size;

    private Long aisleID;

    private Long rowID;

    private Long shelfID;

    public Bin(final Long binID, final Double size, final Long aisleID, final Long rowID, final Long shelfID){
        Preconditions.noneNull(binID,size,aisleID,rowID,shelfID);
        this.binID=binID;
        this.size=size;
        this.aisleID=aisleID;
        this.rowID=rowID;
        this.shelfID=shelfID;
    }

    protected Bin(){}

    public Double size() {
        return size;
    }

    public Long aisleID() {
        return aisleID;
    }

    public Long rowID() {
        return rowID;
    }

    public Long shelfID() {
        return shelfID;
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
