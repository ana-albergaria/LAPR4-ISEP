package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long agvID;

    @Embedded
    @AttributeOverride(name = "autStatus", column = @Column(name = "Autonomy"))
    private AutonomyStatus autonomyStatus;

    @Embedded
    @AttributeOverride(name = "tStatus", column = @Column(name = "Task"))
    private TaskStatus taskStatus;

    private Model modelID;

    @OneToOne
    private AgvDock agvDock;

    public AGV(final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final Model modelID, final AgvDock agvDock){
        Preconditions.noneNull(autonomyStatus, taskStatus, modelID);
        this.autonomyStatus = autonomyStatus;
        this.taskStatus = taskStatus;
        this.modelID = modelID;
        this.agvDock = agvDock;
    }

    protected AGV(){

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Long other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public Long identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(Long id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}
