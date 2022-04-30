package eapli.base.app.bootstrap;

import eapli.base.app.common.console.BaseApplication;
import eapli.base.clientusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.base.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.base.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.base.infrastructure.bootstrapers.AppBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.AppDemoBootstrapper;
import eapli.base.infrastructure.bootstrapers.demo.smoketests.AppSampleEndToEndSmokeTester;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.eventpubsub.EventDispatcher;
import eapli.framework.io.util.Console;
import eapli.framework.util.ArrayPredicates;

//importante
public class AppBootstrap extends BaseApplication {

    private AppBootstrapper masterBootstrapper;

    private AppDemoBootstrapper demoBootstrapper;

    private AppSampleEndToEndSmokeTester endToEndSmokeTester;

    //private AppDemoSmokeTester demoSmokeTester;

    //verificar
    private SignupAcceptedWatchDog signupAcceptedWatchDog;

    //verificar
    private NewUserRegisteredFromSignupWatchDog newUserRegisteredFromSignupWatchDog;

    private boolean isToBootstrapDemoData;
    private boolean isToRunSampleE2E;
    private boolean isToRunSmokeTests;
    private boolean isToWaitInTheEnd;

    public static void main(final String[] args){
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
                new PlainTextEncoder());

        new AppBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args){
        handleArgs(args);

        System.out.println("\n\n------- MASTER DATA -------");
        masterBootstrapper.execute();

        if (isToBootstrapDemoData) {
            System.out.println("\n\n------- DEMO DATA -------");
            demoBootstrapper.execute();
        }

        if (isToRunSampleE2E) {
            System.out.println("\n\n------- BASIC SCENARIO -------");
            endToEndSmokeTester.execute();
        }

        /*if (isToRunSmokeTests) {
            System.out.println("\n\n------- SPECIFIC FEATURES -------");
            demoSmokeTester.execute();
        }*/

        if (isToWaitInTheEnd) {
            Console.readLine("\n\n>>>>>> Enter to finish the program.");
        }

    }

    private void handleArgs(final String[] args){
        isToRunSmokeTests = ArrayPredicates.contains(args, "-smoke:basic");
        isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:e2e");
        if (isToRunSampleE2E || isToRunSmokeTests) {
            isToBootstrapDemoData = true;
        } else {
            isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
        }

        isToWaitInTheEnd = ArrayPredicates.contains(args,"-wait");
    }

    @Override
    protected String appTitle(){
        return "Bootstrapping app data";
    }

    @Override
    protected String appGoodbye(){
        return "Bootstrap data done.";
    }

    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher){
        dispatcher.subscribe(newUserRegisteredFromSignupWatchDog, NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(signupAcceptedWatchDog, SignupAcceptedEvent.class);
    }

}
