package eapli.base.ordermanagement.domain;

import eapli.base.warehousemanagement.domain.AGV;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TheTask implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long taskID;

    @OneToOne
    @JoinColumn(name="agvID", referencedColumnName = "ID")
    private AGV agv;

    @OneToOne
    @JoinColumn(name="orderID", referencedColumnName = "orderId")
    private TheOrder order;

    public TheTask(final AGV agv, final TheOrder order) {
        Preconditions.noneNull(agv, order);
        this.agv=agv;
        this.order=order;
    }

    public TheTask() {
        //empty
    }

    public AGV agv() {
        return this.agv;
    }

    public TheOrder order() {
        return this.order;
    }


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.taskID;
    }

    @Override
    public int compareTo(Long other) {
        return DomainEntities.hashCode(this);
    }

    @Override
    public String toString() {return "Task id: " + this.taskID;};
}
