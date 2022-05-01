# 1900 - "As Project Manager, I intend that, for demonstration purposes, the system has the possibility of being initialized (bootstrap) with some information related to the product catalog and auxiliary information (e.g., categories of products)."



# 1. Requisitos


**UC1900:** As Project Manager, I intend that, for demonstration purposes, the system has the possibility of being initialized (bootstrap) with some information related to the product catalog and auxiliary information (e.g., categories of products).

A interpretação feita deste requisito foi no sentido de popular as tabelas da base de dados, de forma a facilitar a demonstração da aplicação.

# 2. Análise

## 2.1. Dados de bootstrap necessários

* Product Categories
* Products
* Clients


# 3. Design

n/a


## 3.1. Organização dos Bootstrappers

n/a


# 4. Implementação

## 4.1. Classe BaseBootstrap


    private BaseBootstrap() {
    }

    private boolean isToBootstrapDemoData;
    private boolean isToRunSampleE2E;

    public static void main(final String[] args) {

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(),
                new PlainTextEncoder());

        new BaseBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        handleArgs(args);

        System.out.println("\n\n------- MASTER DATA -------");
        new BaseBootstrapper().execute();

        System.out.println("\n\n------- DEMO DATA -------");
        new BaseDemoBootstrapper().execute();

        System.out.println("\n\n------- BASIC SCENARIO -------");
        new BaseDemoSmokeTester().execute();

    }

    private void handleArgs(final String[] args) {
        isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:basic");
        if (isToRunSampleE2E) {
            isToBootstrapDemoData = true;
        } else {
            isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
        }
    }

    @Override
    protected String appTitle() {
        return "Bootstrapping Base data ";
    }

    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }


# 5. Integração/Demonstração

Esta User Story foi implementada na totalidade, sendo dependente de grande parte das restantes USs, uma vez que para popular as tabelas é necessário que as funcionalidades para criação dos objectos estejam funcionais.

# 6. Observações

n/a