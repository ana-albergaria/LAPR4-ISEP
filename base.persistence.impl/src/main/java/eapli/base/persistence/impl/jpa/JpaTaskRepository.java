package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.Task;
import eapli.base.ordermanagement.repositories.TaskRepository;

public class JpaTaskRepository extends BaseJpaRepositoryBase<Task, Long, Long> implements TaskRepository {
    JpaTaskRepository() {
        super("Task ID");
    }

}
