package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AGV implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @Column(name = "ID")
    private Long agvID;

    @Embedded
    @AttributeOverride(name = "autStatus", column = @Column(name = "Autonomy"))
    private AutonomyStatus autonomyStatus;

    @Embedded
    @AttributeOverride(name = "tStatus", column = @Column(name = "Task"))
    private TaskStatus taskStatus;

    @Embedded
    private Model modelID;

    @OneToOne
    @JoinColumn(name = "AGVDockID", referencedColumnName = "agvDockID")
    private AgvDock agvDock;

    public AGV(final Long agvID, final AutonomyStatus autonomyStatus, final TaskStatus taskStatus, final Model modelID, final AgvDock agvDock){
        Preconditions.noneNull(agvID, autonomyStatus, taskStatus, modelID);
        this.agvID = agvID;
        this.autonomyStatus = autonomyStatus;
        this.taskStatus = taskStatus;
        this.modelID = modelID;
        this.agvDock = agvDock;
    }

    protected AGV(){

    }

    public Long getAgvID() {
        return agvID;
    }

    public AutonomyStatus getAutonomyStatus() {
        return autonomyStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Model getModelID() {
        return modelID;
    }

    public AgvDock getAgvDock() {
        return agvDock;
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
