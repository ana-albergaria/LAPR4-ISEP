package eapli.base.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.strings.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//importante
public abstract class BootstrapperBase implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapperBase.class);

    private AuthenticationService authenticationService;

    protected boolean bootstrap(final Action... actions){
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping {}..." + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    protected void authenticateForBootstrapping(){
        authenticationService.authenticate(MasterDataConstants.POWERUSER,
                MasterDataConstants.PASSWORD1);
    }

    protected String nameOfEntity(final Action boot){
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name,name.length() - "Botstrapper".length());
    }

}
