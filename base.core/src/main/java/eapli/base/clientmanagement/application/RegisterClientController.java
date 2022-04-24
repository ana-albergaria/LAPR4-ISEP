package eapli.base.clientmanagement.application;

import eapli.base.clientmanagement.domain.*;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

public class RegisterClientController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientRepository repository = PersistenceContext.repositories().clients();

    public Client registerClient(final String firstNames, final String surnames, final String email,
                                 final String phoneNumber, final String vat ) {
        return registerClient(firstNames, surnames, email, phoneNumber, vat,null,null);
    }

    public Client registerClient(final String firstNames, final String surnames, final String email,
                                 final String phoneNumber, final String vat, final Calendar birthDate, final Client.Gender gender) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        final var newClient = new ClientBuilder()
                .named(new Name(firstNames,surnames))
                .withEmail(new Email(email))
                .withPhoneNumber(new PhoneNumber(phoneNumber))
                .withVAT(new VAT(vat))
                //.withBirthdate(birthDate)
                //.withGender(gender)
                .build();

        return repository.save(newClient);
    }

}
