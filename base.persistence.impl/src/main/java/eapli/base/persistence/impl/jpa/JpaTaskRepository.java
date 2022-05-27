package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.warehousemanagement.domain.AGV;

import java.util.HashMap;
import java.util.Map;

public class JpaTaskRepository extends BaseJpaRepositoryBase<TheTask, Long, Long> implements TaskRepository {
    JpaTaskRepository() {
        super("Task ID");
    }

    @Override
    public Iterable<TheTask> findByAgv(AGV agv) {
        final Map<String, Object> params = new HashMap<>();
        params.put("task", agv);
        return match("e.agv=:task", params);
    }
}
