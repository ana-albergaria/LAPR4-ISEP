package eapli.base.persistence.impl.inmemory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryTaskRepository extends InMemoryDomainRepository<TheTask, Long> implements TaskRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<TheTask> findAll(){
        TaskRepository repository = PersistenceContext.repositories().tasks();

        List<TheTask> list = new LinkedList<>();

        for(TheTask task : repository.findAll()){
            list.add(task);
        }

        return list;
    }
}
