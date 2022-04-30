package eapli.base.infrastructure.bootstrapers.demo.smoketests;

import eapli.base.infrastructure.bootstrapers.BootstrapperBase;
import eapli.base.infrastructure.bootstrapers.demo.smoketests.appuser.AppUserBootstrapper;
import eapli.framework.actions.Action;

//importante
public class AppSampleEndToEndSmokeTester extends BootstrapperBase {

    private AppUserBootstrapper appUserBootstrapper;

    @Override
    public boolean execute(){
        authenticateForBootstrapping();;
        if (!sampleEndToEndScenario()){
            return false;
        }
        return true;
    }

    private boolean sampleEndToEndScenario(){
        if (!usersSignupAndApprove()){
            return false;
        }
        return true;
    }

    private boolean usersSignupAndApprove(){
        final Action[] actions = {
                appUserBootstrapper,
        };
        return bootstrap(actions);
    }

}
