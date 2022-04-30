package eapli.base.infrastructure.bootstrapers.masterdata;

import eapli.base.infrastructure.bootstrapers.MasterDataConstants;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

//importante
public class MasterUsersBootstrapperFinal implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterUsersBootstrapperFinal.class);

    private final UserManagementService userSvc;

    public MasterUsersBootstrapperFinal(final UserManagementService userSvc){
        this.userSvc = userSvc;
    }

    @Override
    public boolean execute(){
        return registerPowerUser();
    }

    private boolean registerPowerUser(){
        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.POWER_USER);
            final SystemUser poweruser = userSvc.registerNewUser(MasterDataConstants.POWERUSER,
                    MasterDataConstants.PASSWORD1, "joe", "power", "joe@email.org", roles);
            assert  poweruser != null;
            LOGGER.debug(String.valueOf(poweruser));
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})",
                    MasterDataConstants.POWERUSER, e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
            return false;
        }
    }

}
