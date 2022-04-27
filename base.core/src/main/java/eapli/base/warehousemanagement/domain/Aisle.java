package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Aisle implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Long aisleID;


    @OneToOne
    @JoinColumn(name = "begin_square_id")
    private Square beginSquare;

    @OneToOne
    @JoinColumn(name = "end_square_id")
    private Square endSquare;

    @OneToOne
    @JoinColumn(name = "depth_square_id")
    private Square depthSquare;

    @Embedded
    private Accessibility accessibility;

    public Aisle(Long aisleId, Square beginSquare, Square endSquare,Square depthSquare, Accessibility accessibility){
        Preconditions.noneNull(aisleId, beginSquare, endSquare, depthSquare, accessibility);
        this.aisleID=aisleId;
        this.beginSquare=beginSquare;
        this.endSquare=endSquare;
        this.depthSquare=depthSquare;
        this.accessibility=accessibility;
    }

    protected Aisle() {
        this.aisleID=null;
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
        return this.aisleID;
    }
}
