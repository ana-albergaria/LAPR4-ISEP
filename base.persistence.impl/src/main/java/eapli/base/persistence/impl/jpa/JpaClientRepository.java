package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;

public class JpaClientRepository extends BaseJpaRepositoryBase<Client, Long, Long>
    implements ClientRepository {

    JpaClientRepository() {
        super("clientId");
    }
}
