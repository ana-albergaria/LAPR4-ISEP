package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.TheOrder;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderRepository extends DomainRepository<Long, TheOrder> {
}