package eapli.base.persistence.impl.inmemory;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryClientRepository extends InMemoryDomainRepository<Client, Long> implements ClientRepository {

    static {
        InMemoryInitializer.init();
    }
}
