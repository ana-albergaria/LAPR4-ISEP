package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.BootstrapperBase;
import eapli.base.infrastructure.bootstrapers.demo.backoffice.ProductCategoriesBootstrapper;
import eapli.framework.actions.Action;

//importante
public class AppDemoBootstrapper extends BootstrapperBase {

    private ProductCategoriesBootstrapper productCategoriesBootstrapper;

    @Override
    public boolean execute(){
        authenticateForBootstrapping();

        if (!bootstrapDemoData()){
            return false;
        }

        // nothing more to do; everything went well
        return true;
    }

    private boolean bootstrapDemoData(){
        if (!backofficeDemoData()){
            return false;
        }
        return true;
    }

    private boolean backofficeDemoData(){
        final Action[] actions = {
                productCategoriesBootstrapper,
                //+ outros
        };
        return bootstrap(actions);
    }

}
