package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Task;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryTaskRepository extends InMemoryDomainRepository<Task, Long> implements TaskRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Task> findAll(){
        TaskRepository repository = PersistenceContext.repositories().tasks();

        List<Task> list = new LinkedList<>();

        for(Task task : repository.findAll()){
            list.add(task);
        }

        return list;
    }
}
