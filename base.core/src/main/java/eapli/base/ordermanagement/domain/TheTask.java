package eapli.base.ordermanagement.domain;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.repositories.BinRepository;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @OneToMany
    private List<Bin> bins;

    public TheTask(final AGV agv, final TheOrder order) {
        Preconditions.noneNull(agv, order);
        this.agv=agv;
        this.order=order;
        this.bins=binsToSend(order);
    }

    private List<Bin> binsToSend(TheOrder order){
        BinRepository repository = PersistenceContext.repositories().bins();
        List<Bin> result = new ArrayList<>();
        Bin binToAdd;
        for (OrderItem item:
             order.orderItems()) {
            for (int i = 0; i < item.quantity(); i++) {
                binToAdd = repository.findInStockByProduct(item.product()).iterator().next();
                binToAdd.changeStatus(Bin.BinStatus.OUT_OF_STOCK);
                result.add(binToAdd);
            }
        }
        return result;
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
