package eapli.base.app.backoffice.console.presentation.agv;

import eapli.framework.presentation.console.AbstractUI;

public class ConfigureAvailableAGVUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Configure Available AGV(s)";
    }
}
