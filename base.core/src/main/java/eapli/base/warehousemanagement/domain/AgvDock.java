package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class AgvDock implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long agvDockID;


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

    public AgvDock(Square beginSquare, Square endSquare,Square depthSquare, Accessibility accessibility){
        Preconditions.noneNull(beginSquare, endSquare, depthSquare, accessibility);
        this.beginSquare=beginSquare;
        this.endSquare=endSquare;
        this.depthSquare=depthSquare;
        this.accessibility=accessibility;
    }

    protected AgvDock() {
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
        return this.agvDockID;
    }
}
