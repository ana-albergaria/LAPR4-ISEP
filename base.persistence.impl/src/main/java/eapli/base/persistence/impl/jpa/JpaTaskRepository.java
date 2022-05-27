package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.TaskRepository;

public class JpaTaskRepository extends BaseJpaRepositoryBase<TheTask, Long, Long> implements TaskRepository {
    JpaTaskRepository() {
        super("Task ID");
    }

}
