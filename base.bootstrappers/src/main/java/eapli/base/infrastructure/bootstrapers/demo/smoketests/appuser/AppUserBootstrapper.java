package eapli.base.infrastructure.bootstrapers.demo.smoketests.appuser;

import eapli.base.clientusermanagement.application.AcceptRefuseSignupRequestController;
import eapli.base.clientusermanagement.domain.SignupRequest;
import eapli.base.infrastructure.bootstrapers.demo.DemoDataConstants;
import eapli.base.myclientuser.application.SignupController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

//importante
public class AppUserBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserBootstrapper.class);

    private SignupController signupController;

    private AcceptRefuseSignupRequestController acceptController;

    @Override
    public boolean execute(){
        signupAndApprove(DemoDataConstants.USER_TEST1, DemoDataConstants.PASSWORD1, "John", "Smith", "john@smith.com",
                DemoDataConstants.USER_TEST1);
        signupAndApprove("isep959", DemoDataConstants.PASSWORD1, "Mary", "Smith", "mary@smith.com", "isep959");

        return true;
    }

    private SignupRequest signupAndApprove(final String username, final String password, final String firstName,
                                           final String lastName, final String email,
                                           final String mecanographicNumber) {
        SignupRequest request = null;
        try {
            request = signupController.signup(username, password, firstName, lastName, email, mecanographicNumber);
            acceptController.acceptSignupRequest(request);
            LOGGER.debug(username);
        } catch (final ConcurrencyException | IntegrityViolationException
                | TransactionSystemException e) {
            LOGGER.error("This should not happen", e);
        }
        return request;
    }

}
