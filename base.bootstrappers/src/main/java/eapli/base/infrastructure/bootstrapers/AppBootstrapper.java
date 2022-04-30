package eapli.base.infrastructure.bootstrapers;

import eapli.base.infrastructure.bootstrapers.masterdata.MasterUsersBootstrapperFinal;
import eapli.framework.actions.Action;

//importante
public class AppBootstrapper extends BootstrapperBase {

    private MasterUsersBootstrapperFinal masterUsersBootstrapperFinal;

    @Override
    public boolean execute(){
        if (!createPowerUser()){
            return false;
        }

        authenticateForBootstrapping();

        // nothing more to do; everything went well
        return true;
    }

    private boolean createPowerUser(){
        return bootstrap(masterUsersBootstrapperFinal);
    }
}
