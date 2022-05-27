package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.Task;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.framework.domain.repositories.DomainRepository;

public interface TaskRepository extends DomainRepository<Long, Task> {
    @Override
    Iterable<Task> findAll();
}
