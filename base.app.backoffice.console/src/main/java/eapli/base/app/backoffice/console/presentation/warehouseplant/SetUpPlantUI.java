package eapli.base.app.backoffice.console.presentation.warehouseplant;

import eapli.base.warehousemanagement.application.SetUpPlantController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;

public class SetUpPlantUI extends AbstractUI {
    private final SetUpPlantController controller = new SetUpPlantController();

    @Override
    protected boolean doShow() {
        this.controller.setUpPlant(new File("base.core\\src\\main\\java\\eapli\\base\\warehousemanagement\\application\\warehouse1.json"));


        return false;
    }

    @Override
    public String headline() {
        return "Set Up Warehouse Plant";
    }
}
