package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TaskStatus implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private final String tStatus;

    public TaskStatus(final String tStatus){
        Preconditions.nonEmpty(tStatus, "Task status can neither be null nor empty.");
        this.tStatus = tStatus;
    }

    protected TaskStatus(){
        this.tStatus = "";
    }

    public TaskStatus valueOf(final String status){
        return new TaskStatus(status);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof TaskStatus)) {
            return false;
        } else {
            TaskStatus that = (TaskStatus)o;
            return this.tStatus.equals(that.tStatus);
        }
    }

    public int hashCode() {
        return (new HashCoder()).with(this.tStatus).code();
    }

    public String toString() {
        return String.format("AGV Status: %s \n", tStatus);
    }

    public int compareTo(final TaskStatus o) {
        return this.tStatus.compareTo(o.tStatus);
    }
}
