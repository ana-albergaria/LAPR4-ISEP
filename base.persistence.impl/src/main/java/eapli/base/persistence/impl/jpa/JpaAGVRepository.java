package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.BrandName;
import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.TaskStatus;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import org.springframework.scheduling.config.Task;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JpaAGVRepository extends BaseJpaRepositoryBase<AGV, Long, Long> implements AGVRepository {

    JpaAGVRepository() {
        super("AGV ID");
    }

    @Override
    public Iterable<TaskStatus> findAllAvailable(){
        final TypedQuery<AGV> query = entityManager().createQuery(
                "SELECT a FROM AGV a",
                AGV.class);

        Iterable<AGV> agvs = query.getResultList();
        List<TaskStatus> filteredByTaskStatusList = new LinkedList<>();

        for (AGV agv : agvs){
            if (!filteredByTaskStatusList.contains(agv.getTaskStatus())){
                filteredByTaskStatusList.add(agv.getTaskStatus());
            }
        }
        return filteredByTaskStatusList;
    }

    @Override
    public Iterable<AGV> findByTaskStatus(TaskStatus taskStatus){
        final Map<String, Object> params = new HashMap<>();
        params.put("task", taskStatus);
        return match("e.taskStatus=:task", params);
    }
}
